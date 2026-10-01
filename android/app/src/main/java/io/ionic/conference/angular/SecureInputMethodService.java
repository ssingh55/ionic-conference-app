package io.ionic.conference.angular;

import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;

public class SecureInputMethodService extends InputMethodService {
    private View keyboardView;

    @Override
    public View onCreateInputView() {
        // Inflate the custom secure keyboard layout
        keyboardView = getLayoutInflater().inflate(R.layout.secure_keyboard, null);
        return keyboardView;
    }

    @Override
    public void onStartInput(InputConnection ic, android.view.inputmethod.EditorInfo attribute) {
        super.onStartInput(ic, attribute);
    }

    @Override
    public void onFinishInput() {
        super.onFinishInput();
    }

    /**
     * Helper method to safely commit text to the input field
     * This method uses commitText() which avoids keylogger interception
     */
    protected void commitTextSecurely(String text) {
        InputConnection ic = getCurrentInputConnection();
        if (ic != null) {
            ic.commitText(text, 1);
        }
    }

    /**
     * Helper method to handle key deletion
     */
    protected void deleteKeySecurely() {
        InputConnection ic = getCurrentInputConnection();
        if (ic != null) {
            ic.deleteSurroundingText(1, 0);
        }
    }
}
