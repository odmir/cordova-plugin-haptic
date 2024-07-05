interface HapticPlugin {
    sendHapticFeedback(
        /** Default is 'CONFIRM'
         * The following are only supported on Android API Level 34+:
         *   - 'DRAG_START'
         *   - 'GESTURE_THRESHOLD_ACTIVATE'
         *   - 'GESTURE_THRESHOLD_DEACTIVATE'
         *   - 'SEGMENT_FREQUENT_TICK'
         *   - 'SEGMENT_TICK'
         *   - 'TOGGLE_OFF'
         *   - ‘TOGGLE_ON’
        */
        androidType?:
        'CLOCK_TICK'
        | 'CONFIRM'
        | 'CONTEXT_CLICK'
        | 'DRAG_START'
        | 'GESTURE_END'
        | 'GESTURE_START'
        | 'GESTURE_THRESHOLD_ACTIVATE'
        | 'GESTURE_THRESHOLD_DEACTIVATE'
        | 'KEYBOARD_PRESS'
        | 'KEYBOARD_RELEASE'
        | 'KEYBOARD_TAP'
        | 'LONG_PRESS'
        | 'REJECT'
        | 'SEGMENT_FREQUENT_TICK'
        | 'SEGMENT_TICK'
        | 'TEXT_HANDLE_MOVE'
        | 'TOGGLE_OFF'
        | 'TOGGLE_ON'
        | 'VIRTUAL_KEY'
        | 'VIRTUAL_KEY_RELEASE',
        iosType?:
        /** Default is 'Success' */
        'ImpactLight'
        | 'ImpactMedium'
        | 'ImpactHeavy'
        | 'ImpactSoft'
        | 'ImpactRigid'
        | 'Success'
        | 'Warning'
        | 'Error'
        | 'SelectionChanged',
        /** Default behavior is to print the error message as a console error. */
        errorCallback?: (errorMessage: any) => any
    );
}

interface CordovaPlugins {
    hapticPlugin: HapticPlugin;
}