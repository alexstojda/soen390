package org.wikipedia.wikiSpeedi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.support.annotation.NonNull;

import org.wikipedia.R;
import org.wikipedia.page.NoDimBottomSheetDialog;

public class WikiSpeediDialog extends NoDimBottomSheetDialog {

    public static boolean is_running = false;
    public static int place_holder = 0;
    View sprint_view = getLayoutInflater().inflate(R.layout.dialog_sprint_reader, null);
    public TextView sprint_text = sprint_view.findViewById(R.id.sprint_text);
    public String[] test = {"this", "is", "a", "super", "duper", "test", "that", "is", "fully", "functional.", "good", "job", "Siamak!"};

    private Runnable set_sprint_text = new Runnable() {
        public void run() {
            if (is_running && place_holder < test.length) {
                sprint_text.setText(test[place_holder]);
                place_holder++;
                sprint_text.postDelayed(this, 200);
            }
        }
    };

    public WikiSpeediDialog(@NonNull Context context, final String selectedText) {
        super(context);

        View rootView = LayoutInflater.from(context).inflate(R.layout.dialog_sprint_reader, null);
        setContentView(rootView);

        rootView.findViewById(R.id.close_button)
                .setOnClickListener((v) -> {
                    sprint_text.removeCallbacks(set_sprint_text);
                    resetSprint();
                    dismiss();
                });

        rootView.findViewById(R.id.start_sprint_button)
                .setOnClickListener((v) -> {

                    if (!is_running) {
                        setIsRunning(true);
                        sprint_text = findViewById(R.id.sprint_text);
                        sprint_text.postDelayed(set_sprint_text, 0);
                    }
                });

        rootView.findViewById(R.id.reset_sprint_button)
                .setOnClickListener((v) -> {
                    setIsRunning(false);
                    resetSprint();
                });

        rootView.findViewById(R.id.stop_sprint_button)
                .setOnClickListener((v) -> {
                    setIsRunning(false);
                });
    }

    public void setIsRunning(boolean is_running) {
        this.is_running = is_running;
    }

    public void resetSprint() {
        this.place_holder = 0;
        this.is_running = false;
        sprint_text.setText("- start -");
    }

    public boolean getIsRunning() {
        return this.is_running;
    }

    public int getPlaceHolder() {
        return this.place_holder;
    }

    public String getSprintText() {
        return this.sprint_text.getText().toString();
    }
}