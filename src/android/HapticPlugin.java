package com.wrobins.cordova.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.os.Build;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Field;


public class HapticPlugin extends CordovaPlugin {
    private View cordovaWebView;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        cordovaWebView = webView.getView();
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if ("sendHapticFeedback".equals(action)) {
            try {
                String hType = args.getString(0);
                Integer hapticFeedbackType = null; // HapticFeedbackConstants.NO_HAPTICS not available in API Lvl 33
                switch (hType) {
                    case "CLOCK_TICK":
                        hapticFeedbackType = HapticFeedbackConstants.CLOCK_TICK;
                        break;
                    case "CONFIRM":
                        hapticFeedbackType = HapticFeedbackConstants.CONFIRM;
                        break;
                    case "CONTEXT_CLICK":
                        hapticFeedbackType = HapticFeedbackConstants.CONTEXT_CLICK;
                        break;
                    case "GESTURE_END":
                        hapticFeedbackType = HapticFeedbackConstants.GESTURE_END;
                        break;
                    case "GESTURE_START":
                        hapticFeedbackType = HapticFeedbackConstants.GESTURE_START;
                        break;
                    case "KEYBOARD_PRESS":
                        hapticFeedbackType = HapticFeedbackConstants.KEYBOARD_PRESS;
                        break;
                    case "KEYBOARD_RELEASE":
                        hapticFeedbackType = HapticFeedbackConstants.KEYBOARD_RELEASE;
                        break;
                    case "KEYBOARD_TAP":
                        hapticFeedbackType = HapticFeedbackConstants.KEYBOARD_TAP;
                        break;
                    case "LONG_PRESS":
                        hapticFeedbackType = HapticFeedbackConstants.LONG_PRESS;
                        break;
                    case "REJECT":
                        hapticFeedbackType = HapticFeedbackConstants.REJECT;
                        break;
                    case "TEXT_HANDLE_MOVE":
                        hapticFeedbackType = HapticFeedbackConstants.TEXT_HANDLE_MOVE;
                        break;
                    case "VIRTUAL_KEY":
                        hapticFeedbackType = HapticFeedbackConstants.VIRTUAL_KEY;
                        break;
                    case "VIRTUAL_KEY_RELEASE":
                        hapticFeedbackType = HapticFeedbackConstants.VIRTUAL_KEY_RELEASE;
                }

                if (hapticFeedbackType == null && Build.VERSION.SDK_INT >= 34) { // only runs for API Level 34+
                    hapticFeedbackType = checkFeedbackType_API34(hType);
                }

                // Bail if feedback type is unknown
                if (hapticFeedbackType == null) {
                    callbackContext.error("Unknown HapticFeedbackType: " + hType);
                    return true;
                }

                this.cordovaWebView.performHapticFeedback(hapticFeedbackType);
                callbackContext.success();
            }
            catch (Exception ex) {
                callbackContext.error(ex.getMessage());
            }
            return true;
        }
        return false;
    }

    @RequiresApi(api = 34)
    private Integer checkFeedbackType_API34(String hType) throws NoSuchFieldException, IllegalAccessException {
        // Constants: DRAG_START, GESTURE_THRESHOLD_ACTIVATE, GESTURE_THRESHOLD_DEACTIVATE,
        //   SEGMENT_FREQUENT_TICK, SEGMENT_TICK, TOGGLE_OFF, TOGGLE_ON
        Field field = HapticFeedbackConstants.class.getField(hType);
        return (Integer) field.get(null);
    }
}