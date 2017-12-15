LOCAL_PATH:= $(call my-dir)

LOCAL_C_INCLUDES := $(LOCAL_PATH) \
                    $(LOCAL_PATH)/bitmap \
					$(LOCAL_PATH)/beautify

LOCAL_MODULE := MagicBeautify
LOCAL_SRC_FILES = MagicJni.cpp \
                  ./bitmap/BitmapOperation.cpp \
                  ./bitmap/Conversion.cpp \
                  ./beautify/MagicBeautify.cpp

LOCAL_LDLIBS := -llog -landroid -lEGL -lGLESv3 -ljnigraphics
LOCAL_LDLIBS += -latomic

include $(BUILD_SHARED_LIBRARY)