package io.ionic.conference.angular;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;

public class SecureInputMethodService extends InputMethodService {
    @Override
    public View onCreateInputView() {
        // Inflate the custom keyboard layout
        View view = getLayoutInflater().inflate(R.layout.secure_keyboard, null);
        
        // Wire up key buttons to commitText() — no external library, no logging
        wireUpKeyButtons(view);
        
        return view;
    }

    private void wireUpKeyButtons(View view) {
        // Get all buttons from the layout and set click listeners
        // Each button will commit its associated character/text directly
        if (view != null) {
            setButtonListener(view, R.id.button_0, "0");
            setButtonListener(view, R.id.button_1, "1");
            setButtonListener(view, R.id.button_2, "2");
            setButtonListener(view, R.id.button_3, "3");
            setButtonListener(view, R.id.button_4, "4");
            setButtonListener(view, R.id.button_5, "5");
            setButtonListener(view, R.id.button_6, "6");
            setButtonListener(view, R.id.button_7, "7");
            setButtonListener(view, R.id.button_8, "8");
            setButtonListener(view, R.id.button_9, "9");
            setButtonListener(view, R.id.button_delete, null);
            setButtonListener(view, R.id.button_space, " ");
        }
    }

    private void setButtonListener(View rootView, int buttonId, final String text) {
        Button button = rootView.findViewById(buttonId);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InputConnection inputConnection = getCurrentInputConnection();
                    if (inputConnection != null) {
                        if (text == null) {
                            // Delete key
                            inputConnection.deleteSurroundingText(1, 0);
                        } else {
                            // Commit the text directly without caching or logging
                            inputConnection.commitText(text, 1);
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onStartInput(InputConnection ic, android.view.inputmethod.EditorInfo attribute) {
        super.onStartInput(ic, attribute);
    }

    @Override
    public void onStartInputView(android.view.inputmethod.EditorInfo attribute, boolean restarting) {
        super.onStartInputView(attribute, restarting);
    }

    @Override
    public void onFinishInputView(boolean finishingInput) {
        super.onFinishInputView(finishingInput);
    }
}
