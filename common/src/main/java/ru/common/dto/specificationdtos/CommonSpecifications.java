package ru.common.dto.specificationdtos;

import static ru.common.dto.specificationdtos.JoinSpecification.*;
import static ru.common.enums.SearchType.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.PluralAttribute;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import ru.common.dto.specificationdtos.search.SearchDateObject;
import ru.common.dto.specificationdtos.search.SearchStringObject;
import ru.common.utils.DateUtils;

@UtilityClass
public class CommonSpecifications {

    public static <T, V> Specification<T> equal(String attr, V value) {
        if (value == null) {
            return null;
        } else if (value instanceof String) {
            if (ObjectUtils.isEmpty(value)) {
                return null;
            }
        }
        return (root, query, cb) -> cb.equal(root.get(attr), value);
    }

    public static <T, V> Specification<T> notEqual(String attr, V value) {
        if (value == null) {
            return null;
        } else if (value instanceof String) {
            if (ObjectUtils.isEmpty(value)) {
                return null;
            }
        }
        return (root, query, cb) -> cb.notEqual(root.get(attr), value);
    }

    public static <T> Specification<T> contain(String attr, String... value) {
        if (value == null) {
            return null;
        }
        return CommonSpecifications.contain(attr, Arrays.asList(value));
    }

    public static <T> Specification<T> contain(
            String attr, Collection<String> values, String... tableLvs) {

        if (values == null || values.isEmpty()) {
            return null;
        }

        final List<String> args =
                values.stream().filter(e -> !ObjectUtils.isEmpty(e)).collect(Collectors.toList());

        if (args.isEmpty()) {
            return null;
        }

        return (root, query, cb) -> {
            final Path path;

            int level = tableLvs != null ? tableLvs.length : 0;

            switch (level) {
                case 0:
                    path = root.get(attr);
                    break;
                case 1:
                    Join join = getJoin(tableLvs[0], root, query.getResultType());
                    path = join.get(attr);
                    break;
                case 2:
                    Join joinLevelOne =
                            getJoinLevelOne(tableLvs[0], tableLvs[1], root, query.getResultType());
                    path = joinLevelOne.get(attr);
                    break;
                case 3:
                    Join joinLevelTwo =
                            getJoinLevelTwo(
                                    tableLvs[0],
                                    tableLvs[1],
                                    tableLvs[2],
                                    root,
                                    query.getResultType());
                    path = joinLevelTwo.get(attr);
                    break;
                case 4:
                    Join joinLevelThree =
                            getJoinLevelThree(
                                    tableLvs[0],
                                    tableLvs[1],
                                    tableLvs[2],
                                    tableLvs[3],
                                    root,
                                    query.getResultType());
                    path = joinLevelThree.get(attr);
                    break;
                default:
                    path = null;
                    break;
            }

            return cb.or(
                    args.stream()
                            .map(
                                    e -> {
                                        Expression<String> lower = cb.lower(path);
                                        return cb.like(lower, "%" + e.toLowerCase() + "%");
                                    })
                            .toArray(Predicate[]::new));
        };
    }

    public static <T> Specification<T> startsWith(String attr, String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return (root, query, cb) -> cb.like(cb.lower(root.get(attr)), value.toLowerCase() + "%");
    }

    public static <T> Specification<T> start(String attr, String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return (root, query, cb) -> cb.like(cb.lower(root.get(attr)), value.toLowerCase() + "%");
    }

    public static <T> Specification<T> end(String attr, String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return (root, query, cb) -> cb.like(cb.lower(root.get(attr)), "%" + value.toLowerCase());
    }

    public static <T, V> Specification<T> in(String attr, Collection<V> value) {
        if (CollectionUtils.isEmpty(value)) {
            return null;
        }
        return (root, query, cb) -> root.get(attr).in(value);
    }

    public static <T, V> Specification<T> in(String attr, Collection<V> value, String... tableLvs) {
        if (CollectionUtils.isEmpty(value)) {
            return null;
        }
        return (root, query, cb) -> {
            final Path path;

            int level = tableLvs != null ? tableLvs.length : 0;

            switch (level) {
                case 0 -> path = root.get(attr);
                case 1 -> {
                    Join join = getJoin(tableLvs[0], root, query.getResultType());
                    path = join.get(attr);
                }
                case 2 -> {
                    Join joinLevelOne =
                            getJoinLevelOne(tableLvs[0], tableLvs[1], root, query.getResultType());
                    path = joinLevelOne.get(attr);
                }
                case 3 -> {
                    Join joinLevelTwo =
                            getJoinLevelTwo(
                                    tableLvs[0],
                                    tableLvs[1],
                                    tableLvs[2],
                                    root,
                                    query.getResultType());
                    path = joinLevelTwo.get(attr);
                }
                case 4 -> {
                    Join joinLevelThree =
                            getJoinLevelThree(
                                    tableLvs[0],
                                    tableLvs[1],
                                    tableLvs[2],
                                    tableLvs[3],
                                    root,
                                    query.getResultType());
                    path = joinLevelThree.get(attr);
                }
                default -> path = null;
            }

            return path.in(value);
        };
    }

    public static <T, V> Specification<T> notIn(String attr, List<V> value) {
        if (CollectionUtils.isEmpty(value)) {
            return null;
        }
        return (root, query, cb) -> root.get(attr).in(value).not();
    }

    public static <T, V> Specification<T> isNull(String attr) {
        return (root, query, cb) -> cb.isNull(root.get(attr));
    }

    public static <T, V> Specification<T> isNotNull(String attr) {
        return (root, query, cb) -> cb.isNotNull(root.get(attr));
    }

    public static <T> Specification<T> isTrue(String attr) {
        return (root, query, cb) -> cb.isTrue(root.get(attr));
    }

    public static <T> Specification<T> isFalse(String attr) {
        return (root, query, cb) -> cb.isFalse(root.get(attr));
    }

    public static <T> Specification<T> isNotFalse(String attr) {
        return (root, query, cb) -> cb.or(cb.isTrue(root.get(attr)), cb.isNull(root.get(attr)));
    }

    public static <T> Specification<T> isNotTrue(String attr) {
        return (root, query, cb) -> cb.or(cb.isFalse(root.get(attr)), cb.isNull(root.get(attr)));
    }

    public static <T> Specification<T> dateTimeAfter(String attr, LocalDateTime value) {
        if (value == null) {
            return null;
        }
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(attr), value);
    }

    public static <T> Specification<T> dateTimeBefore(String attr, LocalDateTime value) {
        if (value == null) {
            return null;
        }
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(attr), value);
    }

    public static <T> Specification<T> is(String attr, Boolean value) {
        if (value == null) {
            return null;
        }
        return (root, query, cb) -> value ? cb.isTrue(root.get(attr)) : cb.isFalse(root.get(attr));
    }

    public static <T> Specification<T> is(String attr, String value) {
        if (value != null) {
            if ("true".equalsIgnoreCase(value)) {
                return is(attr, Boolean.TRUE);
            }
            if ("false".equalsIgnoreCase(value)) {
                return is(attr, Boolean.FALSE);
            }
        }
        return null;
    }

    public static <T, E> Specification<T> greaterThan(String attr, Integer value) {
        return (root, query, cb) -> cb.greaterThan(cb.size(root.get(attr)), value);
    }

    public static <T> Specification<T> dateBetween(
            String attr, LocalDateTime from, LocalDateTime to) {
        if (from == null || to == null) {
            return null;
        }
        return (root, query, cb) -> cb.between(root.get(attr), from, to);
    }

    public static <T> Specification<T> dateBetween(String attr, LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            return null;
        }
        return (root, query, cb) -> cb.between(root.get(attr), from, to);
    }

    public static <T> Specification<T> dateBetween(String attr, LocalTime from, LocalTime to) {
        if (from == null || to == null) {
            return null;
        }
        return (root, query, cb) -> cb.between(root.get(attr), from, to);
    }

    public static <T> Specification<T> before(String attr, LocalDateTime threshold) {
        if (threshold == null) {
            return null;
        }
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.<LocalDateTime>get(attr), threshold);
    }

    public static <T> Specification<T> before(String attr, LocalDate threshold) {
        if (threshold == null) {
            return null;
        }
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.<LocalDate>get(attr), threshold);
    }

    public static <T> Specification<T> after(String attr, LocalDateTime threshold) {
        if (threshold == null) {
            return null;
        }
        return (root, query, cb) ->
                cb.greaterThanOrEqualTo(root.<LocalDateTime>get(attr), threshold);
    }

    public static <T> Specification<T> after(String attr, LocalDate threshold) {
        if (threshold == null) {
            return null;
        }
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.<LocalDate>get(attr), threshold);
    }

    public static <T extends Serializable, E> Specification<T> isMember(
            PluralAttribute<T, List<E>, E> attr, E value) {
        return (root, query, cb) -> cb.isMember(value, root.get(attr));
    }

    public static <T> Specification<T> like(String attr, String value) {
        return (root, query, cb) ->
                like(value).map(val -> cb.like(cb.lower(root.get(attr)), val)).orElse(null);
    }

    public static Optional<String> like(String query) {
        if (query == null) {
            return Optional.empty();
        }
        query = query.trim();
        if (query.isEmpty()) {
            return Optional.empty();
        }
        query =
                new StringBuilder(2 + query.length())
                        .append("%")
                        .append(query)
                        .append("%")
                        .toString();
        return Optional.of(query.toLowerCase());
    }

    public static Predicate getStringCriteria(
            String field, SearchStringObject searchStringObject, CriteriaBuilder cb, From root) {
        if (searchStringObject == null) {
            return null;
        }
        return switch (searchStringObject.getSearchType()) {
            case EQUAL -> cb.equal(root.get(field), searchStringObject.getValue());
            case NOT_EQUAL -> cb.notEqual(root.get(field), searchStringObject.getValue());
            case START_WITH -> cb.like(
                    cb.lower(root.get(field)),
                    searchStringObject.getValue().toLowerCase() + "%");
            case CONTAIN -> cb.like(
                    cb.lower(root.get(field)),
                    "%" + searchStringObject.getValue().toLowerCase() + "%");
            case END_WITH -> cb.like(
                    cb.lower(root.get(field)),
                    "%" + searchStringObject.getValue().toLowerCase());
            case EMPTY -> cb.isNull(root.get(field));
            case NOT_EMPTY -> cb.isNotNull(root.get(field));
            case NOT_START_WITH -> cb.notLike(
                    cb.lower(root.get(field)),
                    searchStringObject.getValue().toLowerCase() + "%");
            case NOT_CONTAIN -> cb.notLike(
                    cb.lower(root.get(field)),
                    "%" + searchStringObject.getValue().toLowerCase() + "%");
            case NOT_END_WITH -> cb.notLike(
                    cb.lower(root.get(field)),
                    "%" + searchStringObject.getValue().toLowerCase());
            case GREATER -> cb.greaterThan(
                    root.get(field), new BigDecimal(searchStringObject.getValue()));
            case GREATER_OR_EQUAL -> cb.greaterThanOrEqualTo(
                    root.get(field), new BigDecimal(searchStringObject.getValue()));
            case LESS -> cb.lessThan(root.get(field), new BigDecimal(searchStringObject.getValue()));
            case LESS_OR_EQUAL -> cb.lessThanOrEqualTo(
                    root.get(field), new BigDecimal(searchStringObject.getValue()));
            default -> null;
        };
    }

    public static Predicate getStringCriteriaExpression(
            Expression field,
            SearchStringObject searchStringObject,
            CriteriaBuilder cb,
            From root) {
        if (searchStringObject == null) {
            return null;
        }
        return switch (searchStringObject.getSearchType()) {
            case EQUAL -> cb.equal(field, searchStringObject.getValue());
            case NOT_EQUAL -> cb.notEqual(field, searchStringObject.getValue());
            case START_WITH -> cb.like(cb.lower(field), searchStringObject.getValue().toLowerCase() + "%");
            case CONTAIN -> cb.like(
                    cb.lower(field), "%" + searchStringObject.getValue().toLowerCase() + "%");
            case END_WITH -> cb.like(cb.lower(field), "%" + searchStringObject.getValue().toLowerCase());
            case EMPTY -> cb.isNull(field);
            case NOT_EMPTY -> cb.isNotNull(field);
            case NOT_START_WITH -> cb.notLike(
                    cb.lower(field), searchStringObject.getValue().toLowerCase() + "%");
            case NOT_CONTAIN -> cb.notLike(
                    cb.lower(field), "%" + searchStringObject.getValue().toLowerCase() + "%");
            case NOT_END_WITH -> cb.notLike(
                    cb.lower(field), "%" + searchStringObject.getValue().toLowerCase());
            case GREATER -> cb.greaterThan(field, new BigDecimal(searchStringObject.getValue()));
            case GREATER_OR_EQUAL -> cb.greaterThanOrEqualTo(
                    field, new BigDecimal(searchStringObject.getValue()));
            case LESS -> cb.lessThan(field, new BigDecimal(searchStringObject.getValue()));
            case LESS_OR_EQUAL -> cb.lessThanOrEqualTo(field, new BigDecimal(searchStringObject.getValue()));
            default -> null;
        };
    }

    public static Predicate getDateCriteria(
            String field, SearchDateObject searchStringObject, CriteriaBuilder cb, From root) {
        if (searchStringObject == null) {
            return null;
        }
        switch (searchStringObject.getSearchType()) {
            case EQUAL:
                return cb.equal(root.get(field), searchStringObject.getValue());
            case NOT_EQUAL:
                return cb.notEqual(root.get(field), searchStringObject.getValue());
            case EMPTY:
                return cb.isNull(root.get(field));
            case NOT_EMPTY:
                return cb.isNotNull(root.get(field));
            case GREATER:
                return cb.greaterThan(root.get(field), searchStringObject.getValue());
            case GREATER_OR_EQUAL:
                LocalDateTime minValue =
                        DateUtils.getLocalDateTimeMin(searchStringObject.getValue());
                return cb.greaterThanOrEqualTo(root.get(field), minValue);
            case LESS:
                return cb.lessThan(root.get(field), searchStringObject.getValue());
            case LESS_OR_EQUAL:
                LocalDateTime maxValue =
                        DateUtils.getLocalDateTimeMax(searchStringObject.getValue());
                return cb.lessThanOrEqualTo(root.get(field), maxValue);
            case BETWEEN:
                LocalDateTime value = DateUtils.getLocalDateTimeMin(searchStringObject.getValue());
                LocalDateTime valueTo =
                        DateUtils.getLocalDateTimeMax(searchStringObject.getValueTo());
                return cb.between(root.get(field), value, valueTo);
        }
        return null;
    }



    public static Predicate getDateTimeCriteria(
            String field, SearchDateObject searchStringObject, CriteriaBuilder cb, From root) {
        if (searchStringObject == null) {
            return null;
        }
        List<Predicate> predicates = new ArrayList<>();
        switch (searchStringObject.getSearchType()) {
            case EQUAL:
                LocalDateTime startOfDay =
                        LocalDateTime.of(searchStringObject.getValue(), LocalTime.MIN);
                LocalDateTime endOfDay =
                        LocalDateTime.of(searchStringObject.getValue(), LocalTime.MAX)
                                .truncatedTo(ChronoUnit.MILLIS);
                return cb.between(root.get(field), startOfDay, endOfDay);
            case NOT_EQUAL:
                predicates.add(
                        cb.lessThan(root.get(field), searchStringObject.getValue().atStartOfDay()));
                predicates.add(
                        cb.greaterThan(
                                root.get(field),
                                searchStringObject
                                        .getValue()
                                        .atTime(LocalTime.MAX)
                                        .truncatedTo(ChronoUnit.MILLIS)));
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            case EMPTY:
                return cb.isNull(root.get(field));
            case NOT_EMPTY:
                return cb.isNotNull(root.get(field));
            case GREATER:
                return cb.greaterThan(
                        root.get(field),
                        searchStringObject
                                .getValue()
                                .atTime(LocalTime.MAX)
                                .truncatedTo(ChronoUnit.MILLIS));
            case GREATER_OR_EQUAL:
                return cb.greaterThanOrEqualTo(
                        root.get(field), searchStringObject.getValue().atStartOfDay());
            case LESS:
                return cb.lessThan(root.get(field), searchStringObject.getValue().atStartOfDay());
            case LESS_OR_EQUAL:
                return cb.lessThanOrEqualTo(
                        root.get(field),
                        searchStringObject
                                .getValue()
                                .atTime(LocalTime.MAX)
                                .truncatedTo(ChronoUnit.MILLIS));
            case BETWEEN:
                LocalDateTime value =
                        LocalDateTime.of(searchStringObject.getValue(), LocalTime.MIN);
                LocalDateTime valueTo =
                        LocalDateTime.of(searchStringObject.getValueTo(), LocalTime.MAX)
                                .truncatedTo(ChronoUnit.MILLIS);
                return cb.between(root.get(field), value, valueTo);
        }
        return null;
    }

    public static Predicate getStringCriteria(
            String field, String value, CriteriaBuilder cb, From root) {
        return cb.equal(root.get(field), value);
    }

    public static Predicate getIntCriteria(
            String field, Integer value, CriteriaBuilder cb, From root) {
        return cb.equal(root.get(field), value);
    }

    public static Predicate getBigDecimalCriteria(
            String field, BigDecimal value, CriteriaBuilder cb, From root) {
        return cb.equal(root.get(field), value);
    }
}