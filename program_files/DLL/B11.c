#include <jni.h>
#include "B11.h"


JNIEXPORT jint JNICALL Java_B11_add1(JNIEnv *env, jobject obj, jint a, jint b) {
    return a + b;
}

JNIEXPORT jint JNICALL Java_B11_sub1(JNIEnv *env, jobject obj, jint a, jint b) {
    return a - b;
}

JNIEXPORT jint JNICALL Java_B11_multi(JNIEnv *env, jobject obj, jint a, jint b) {
    return a * b;
}

JNIEXPORT jint JNICALL Java_B11_divi(JNIEnv *env, jobject obj, jint a, jint b) {
    return a / b; 
}
