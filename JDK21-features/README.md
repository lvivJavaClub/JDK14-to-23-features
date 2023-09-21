# JDK20 Features ☕️

![img_1.png](img_1.png)

## 1. Prepare ⚙️

Install Java 21 using `sdk`

```bash
sdk install java 21-open
sdk use java 21-open
```

Link to JDK21 features: https://openjdk.java.net/projects/jdk/21/

## 2. API updates
https://javaalmanac.io/jdk/21/apidiff/20/

## 3. JEPs list 📄

###  430:	String Templates (Preview)
###  431:	Sequenced Collections

---
###  440:	Record Patterns
Future Work
- Varargs patterns, for records of variable arity;
- Unnamed patterns, which can appear in record-pattern pattern lists and which match any value but do not declare pattern variables; and
- Patterns that can apply to values of arbitrary classes rather than only record classes.

###  441:	Pattern Matching for switch
###  443:	Unnamed Patterns and Variables (Preview)
###  445:	Unnamed Classes and Instance Main Methods (Preview)

---
###  444:	Virtual Threads
###  446:	Scoped Values (Preview)
###  453:	Structured Concurrency (Preview)

---
###  439:	Generational ZGC

Applications running with Generational ZGC should enjoy:

- Lower risks of allocations stalls,
- Lower required heap memory overhead, and
- Lower garbage collection CPU overhead.
- Enable Generational ZGC with command line options `-XX:+UseZGC -XX:+ZGenerational`

###  448:	Vector API (Sixth Incubator)
###  449:	Deprecate the Windows 32-bit x86 Port for Removal
###  451:	Prepare to Disallow the Dynamic Loading of Agents
###  452:	Key Encapsulation Mechanism API
###  442:	Foreign Function & Memory API (Third Preview)

---

https://dev.java/
