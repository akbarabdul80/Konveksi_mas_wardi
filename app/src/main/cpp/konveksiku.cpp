#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_zero_konveksiku_utils_Secured_getBannerPubID(JNIEnv *env, jobject clazz) {
    return env->NewStringUTF("ca-app-pub-2187811703921023/3371136978");
}
