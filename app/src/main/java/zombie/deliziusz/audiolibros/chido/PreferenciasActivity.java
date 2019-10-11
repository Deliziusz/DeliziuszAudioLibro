package zombie.deliziusz.audiolibros.chido;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import zombie.deliziusz.audiolibros.chido.fragments.PreferenciasFragment;

/**
 * @author Deliziusz : Karla Yazmín García Pérez
 */

public class PreferenciasActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.
                content, new PreferenciasFragment()).commit();
    }
}