# JDK19 Features

## 1. Prepare

Install Java 19 using `sdk`

```bash
sdk install java 19-open
sdk use java 19-open
```

Link to JDK19 features: https://openjdk.java.net/projects/jdk/19/

---

## 2. JEPs list:
--
* ### JEP 422: Linux/RISC-V Port
* ### JEP 424: Foreign Function & Memory API (Preview)
* ### JEP 426: Vector API (Fourth Incubator)
---
* ### JEP 405: Record Patterns (Preview)
* ### JEP 427: Pattern Matching for switch (Third Preview)
---
* ### JEP 425: Virtual Threads (Preview)
* ### JEP 428: Structured Concurrency (Incubator)
---

## 3. Changes in JDK API beyond the JEPs
- `HashMap.newHashMap`
- `LinkedHashMap.newLinkedHashMap`
- `HashSet.newHashSet`
- `LinkedHashSet.newLinkedHashSet`
- `DateTimeFormatter.ofLocalizedPattern`
- `new Locale("lang");` is deprecated
