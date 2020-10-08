/**
 *
 * -Xlog:gc*=trace for GC logging in trace level
 * -Xlog:gc*:gc.log for detailed logging into a file
 *
 * -XX:+UseZGC to use ZGC
 *
 * -XX:+UseShenandoahGC to use Shenandoah GC
 * Some info:
 *  https://www.reddit.com/r/java/comments/b3cptz/no_shenandoah_gc_in_both_commercial_and_open/
 *  http://mail.openjdk.java.net/pipermail/shenandoah-dev/2018-December/008673.html
 *  https://developers.redhat.com/blog/2019/04/19/not-all-openjdk-12-builds-include-shenandoah-heres-why/
 */
package n03_jep77_ZGC_jep379_Shenandoah;