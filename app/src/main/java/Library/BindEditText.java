package Library;

import android.text.TextWatcher;
import android.util.Pair;
import android.widget.EditText;
import androidx.databinding.BindingAdapter;
import com.bigbrains.userskotlin.R;
import org.w3c.dom.Text;
import Models.BindableString;

public class BindEditText {
    @BindingAdapter({"app:binding"})
    public static void bindEditText(EditText view, final BindableString bindableString) {
        Pair<BindableString, TextWatcherAdapter> pair = (Pair) view.getTag(R.id.bound_observable);
            /*  BindableString = first.
                TextWatcherAdapter = second.
             */
        if(pair == null || pair.first != bindableString){
            if(pair != null){  // contiene información
                // Se remueve el evento de acuerdo al objeto y la prioridad.
                view.removeTextChangedListener(pair.second);
            }

            TextWatcherAdapter watcher = new TextWatcherAdapter(){
                //Se sobreescribe el evento onTextChanged de la clase.
                public void onTextChanged(CharSequence s, int start, int before, int count){
                    bindableString.setValue(s.toString());
                }
            };

            view.setTag(R.id.bound_observable, new Pair<>(bindableString, watcher));
            view.addTextChangedListener(watcher);
        }
        String newValue = bindableString.getValue();
        if(!view.getText().toString().equals(newValue)){
            view.setText(newValue);
        }
    }
}
