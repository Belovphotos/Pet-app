package ru.common.dto.specificationdtos;

import static jakarta.persistence.criteria.JoinType.LEFT;

import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

public class JoinSpecification<T> {

    private static Join getFetchTable(String fetchNameTable, Root<?> root) {
        for (Fetch<?, ?> fetche : root.getFetches()) {
            if (fetchNameTable.equals(fetche.getAttribute().getName())) {
                return (Join) fetche;
            }
        }
        return (Join) root.fetch(fetchNameTable, LEFT);
    }

    private static Join getFetchTableLvl1(String tableLvl1, String fetchTable, Root<?> root) {
        for (Fetch<?, ?> fetcheRoot : root.getFetches()) {
            for (Fetch<?, ?> fetche : fetcheRoot.getFetches()) {
                if (fetchTable.equals(fetche.getAttribute().getName())) {
                    return (Join) fetche;
                }
            }
        }
        return (Join) getFetchTable(tableLvl1, root).fetch(fetchTable, LEFT);
    }

    private static Join getFetchTableLvl2(
            String tableLvl1, String tableLvl2, String fetchTable, Root<?> root) {
        for (Fetch<?, ?> fetcheRoot : root.getFetches()) {
            for (Fetch<?, ?> fetchLvl1 : fetcheRoot.getFetches()) {
                for (Fetch<?, ?> fetchLvl2 : fetchLvl1.getFetches()) {
                    if (fetchTable.equals(fetchLvl2.getAttribute().getName())) {
                        return (Join) fetchLvl2;
                    }
                }
            }
        }
        return (Join) getFetchTable(tableLvl1, root).fetch(tableLvl2, LEFT).fetch(fetchTable, LEFT);
    }

    private static Join getFetchTableLvl3(
            String tableLvl1, String tableLvl2, String tableLvl3, String fetchTable, Root<?> root) {
        for (Fetch<?, ?> fetcheRoot : root.getFetches()) {
            for (Fetch<?, ?> fetchLvl1 : fetcheRoot.getFetches()) {
                for (Fetch<?, ?> fetchLvl2 : fetchLvl1.getFetches()) {
                    for (Fetch<?, ?> fetchLvl3 : fetchLvl2.getFetches()) {
                        if (fetchTable.equals(fetchLvl3.getAttribute().getName())) {
                            return (Join) fetchLvl3;
                        }
                    }
                }
            }
        }
        return (Join)
                getFetchTable(tableLvl1, root)
                        .fetch(tableLvl2, LEFT)
                        .fetch(tableLvl3, LEFT)
                        .fetch(fetchTable, LEFT);
    }

    private static Join getJoinTable(String joinNameTable, Root<?> root) {
        for (Join<?, ?> join : root.getJoins()) {
            if (joinNameTable.equals(join.getAttribute().getName())) {
                return join;
            }
        }
        return root.join(joinNameTable, LEFT);
    }

    private static Join getJoinTableLvl1(String tableLvl1, String joinTable, Root<?> root) {
        for (Join<?, ?> rootJoin : root.getJoins()) {
            for (Join<?, ?> join : rootJoin.getJoins()) {
                if (joinTable.equals(join.getAttribute().getName())) {
                    return join;
                }
            }
        }
        return getJoinTable(tableLvl1, root).join(joinTable, LEFT);
    }

    private static Join getJoinTableLvl2(
            String tableLvl1, String tableLvl2, String joinTable, Root<?> root) {
        for (Join<?, ?> rootJoin : root.getJoins()) {
            for (Join<?, ?> joinLvl1 : rootJoin.getJoins()) {
                for (Join<?, ?> joinLvl2 : joinLvl1.getJoins()) {
                    if (joinTable.equals(joinLvl2.getAttribute().getName())) {
                        return joinLvl2;
                    }
                }
            }
        }
        return getJoinTable(tableLvl1, root).join(tableLvl2, LEFT).join(joinTable, LEFT);
    }

    private static Join getJoinTableLvl3(
            String tableLvl1, String tableLvl2, String tableLvl3, String joinTable, Root<?> root) {
        for (Join<?, ?> rootJoin : root.getJoins()) {
            for (Join<?, ?> joinLvl1 : rootJoin.getJoins()) {
                for (Join<?, ?> joinLvl2 : joinLvl1.getJoins()) {
                    for (Join<?, ?> joinLvl3 : joinLvl2.getJoins()) {
                        if (joinTable.equals(joinLvl3.getAttribute().getName())) {
                            return joinLvl3;
                        }
                    }
                }
            }
        }
        return getJoinTable(tableLvl1, root)
                .join(tableLvl2, LEFT)
                .join(tableLvl3, LEFT)
                .join(joinTable, LEFT);
    }

    public static Join getJoinLevelOne(
            String tableLvl1, String joinOrFetchNameTable, Root<?> root, Class queryResultType) {
        return Long.class != queryResultType
                ? getFetchTableLvl1(tableLvl1, joinOrFetchNameTable, root)
                : getJoinTableLvl1(tableLvl1, joinOrFetchNameTable, root);
    }

    public static Join getJoinLevelTwo(
            String tableLvl1,
            String tableLvl2,
            String joinOrFetchNameTable,
            Root<?> root,
            Class queryResultType) {
        return Long.class != queryResultType
                ? getFetchTableLvl2(tableLvl1, tableLvl2, joinOrFetchNameTable, root)
                : getJoinTableLvl2(tableLvl1, tableLvl2, joinOrFetchNameTable, root);
    }

    public static Join getJoinLevelThree(
            String tableLvl1,
            String tableLvl2,
            String tableLvl3,
            String joinOrFetchNameTable,
            Root<?> root,
            Class queryResultType) {
        return Long.class != queryResultType
                ? getFetchTableLvl3(tableLvl1, tableLvl2, tableLvl3, joinOrFetchNameTable, root)
                : getJoinTableLvl3(tableLvl1, tableLvl2, tableLvl3, joinOrFetchNameTable, root);
    }

    public static Join getJoin(String joinOrFetchNameTable, Root<?> root, Class queryResultType) {
        if (Long.class != queryResultType) {
            return getFetchTable(joinOrFetchNameTable, root);
        } else {
            return getJoinTable(joinOrFetchNameTable, root);
        }
    }
}
