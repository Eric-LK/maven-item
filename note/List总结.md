# 疑惑点

1、 mybatis lambda query list 返回一个空的list，不是null

2、当list为空数组的时候，进行stream的时候，返回的也是空数组

# 排序

```java
public class Test {
    void main() {
        List<ListEntity> entityList = new ArrayList<>(
                Arrays.asList(
                        new ListEntity(1L, "k1", "v1"),
                        new ListEntity(0L, "k1", "v1"),
                        new ListEntity(0L, "k1", "v1"),
                        new ListEntity(0L, "k1", "v1"),
                        new ListEntity(123, "k2", "v1"),
                        new ListEntity(321, "k3", "v2"),
                        new ListEntity(55555L, "k3", "")
                )
        );

        List<ListEntity> collect = entityList.stream().sorted(Comparator.comparing(ListEntity::getId)).collect(Collectors.toList());
    }
}

```

# 过滤去重

```java
public class Test {
    void main() {
        List<ListEntity> entityList = new ArrayList<>(
                Arrays.asList(
                        new ListEntity(1L, "k1", "v1"),
                        new ListEntity(2L, "k2", "v1"),
                        new ListEntity(3L, "k3", "v2"),
                        new ListEntity(3L, "k3", "")
                )
        );

        List<String> valueList = entityList.stream()
                .map(ListEntity::getValue)
                .filter(value -> !StringUtils.isEmpty(value))
                .distinct()
                .collect(Collectors.toList());

        System.out.println(valueList.toString());
    }
}
```

输出：[v1, v2]

# 根据实体某个字段去重

```java
public class Test {
    void main() {
        List<ListEntity> lists = new ArrayList<>(
                Arrays.asList(
                        new ListEntity(1L, "k1", "v1"),
                        new ListEntity(2L, "k2", "v1"),
                        new ListEntity(3L, "k3", "v2"),
                        new ListEntity(3L, "k3", "")
                )
        );
        
        List<ListEntity> disUsers = lists.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(ListEntity::getKey))),
                        ArrayList::new
                )
        );

        Boolean flag = lists.stream().map(ListEntity::getValue).distinct().count() == lists.size();
        System.out.println(flag);

        System.out.println("根据对象中的某个字段进行去重操作" + disUsers);
    }
}
```