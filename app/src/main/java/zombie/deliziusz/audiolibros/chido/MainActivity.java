package zombie.deliziusz.audiolibros.chido;

import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import zombie.deliziusz.audiolibros.chido.fragments.DetalleFragment;
import zombie.deliziusz.audiolibros.chido.fragments.PreferenciasFragment;
import zombie.deliziusz.audiolibros.chido.fragments.SelectorFragment;

/**
 * @author Deliziusz : Karla Yazmín García Pérez
 */

public class    MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    /*private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;*/
    private AdaptadorLibrosFiltro adaptador;
    private AppBarLayout appBarLayout;
    private TabLayout tabs;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( zombie.deliziusz.audiolibros.R.layout.activity_main);
        /*if ((findViewById(R.id.contenedor_pequeno) != null) &&
                (getSupportFragmentManager().findFragmentById(
                        R.id.contenedor_pequeno) == null)){
            SelectorFragment primerFragment = new SelectorFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenedor_pequeno, primerFragment).commit();
        }*/
        int idContenedor = (findViewById(zombie.deliziusz.audiolibros.R.id.contenedor_pequeno) != null) ?
                zombie.deliziusz.audiolibros.R.id.contenedor_pequeno : zombie.deliziusz.audiolibros.R.id.contenedor_izquierdo;
        SelectorFragment primerFragment = new SelectorFragment();
        getSupportFragmentManager().beginTransaction()
                .add(idContenedor, primerFragment)
                .commit();
        Toolbar toolbar = (Toolbar) findViewById(zombie.deliziusz.audiolibros.R.id.toolbar);
        appBarLayout = (AppBarLayout) findViewById(zombie.deliziusz.audiolibros.R.id.appBarLayout);
        tabs =(TabLayout)findViewById(zombie.deliziusz.audiolibros.R.id.tabs);
        drawer = (DrawerLayout) findViewById(zombie.deliziusz.audiolibros.R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this,drawer, toolbar, zombie.deliziusz.audiolibros.R.string.drawer_open, zombie.deliziusz.audiolibros.R.string. drawer_close);
        //Pestañas
        tabs.addTab(tabs.newTab().setText("Todos"));
        tabs.addTab(tabs.newTab().setText("Nuevos"));
        tabs.addTab(tabs.newTab().setText("Leidos"));
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0: //Todos
                        adaptador.setNovedad(false);
                        adaptador.setLeido(false);
                        break;
                    case 1: //Nuevos
                        adaptador.setNovedad(true);
                        adaptador.setLeido(false);
                        break;
                    case 2: //Leidos
                        adaptador.setNovedad(false);
                        adaptador.setLeido(true);
                        break;
                }
                adaptador.notifyDataSetChanged();
            }
            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });

        //Barar de acciones

        setSupportActionBar(toolbar);
        //Botón flotante
        FloatingActionButton fab = (FloatingActionButton) findViewById(zombie.deliziusz.audiolibros.R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action",
                        Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        adaptador = ((Aplicacion) getApplicationContext()).getAdaptador();
        // Navigation Drawer
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(zombie.deliziusz.audiolibros.R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    protected void onNewIntent(Intent intent) {
        if (intent.getAction() != null) {
            if (intent.getAction().equals(Intent.ACTION_SEARCH)) {
                adaptador.setBusqueda(intent.getStringExtra(SearchManager.QUERY));
                adaptador.notifyDataSetChanged();
            }
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == zombie.deliziusz.audiolibros.R.id.nav_todos) {
            adaptador.setGenero("");
            adaptador.notifyDataSetChanged();
        } else if (id == zombie.deliziusz.audiolibros.R.id.nav_epico) {
            adaptador.setGenero(Libro.G_EPICO);
            adaptador.notifyDataSetChanged();
        } else if (id == zombie.deliziusz.audiolibros.R.id.nav_XIX) {
            adaptador.setGenero(Libro.G_S_XIX);
            adaptador.notifyDataSetChanged();
        } else if (id == zombie.deliziusz.audiolibros.R.id.nav_suspense) {
            adaptador.setGenero(Libro.G_SUSPENSE);
            adaptador.notifyDataSetChanged();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(
                zombie.deliziusz.audiolibros.R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(
                zombie.deliziusz.audiolibros.R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(zombie.deliziusz.audiolibros.R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == zombie.deliziusz.audiolibros.R.id.menu_preferencias) {
            /*Intent i = new Intent(this, PreferenciasActivity.class);
            startActivity(i);*/
            abrePreferencias();
            return true;
        } else if (id == zombie.deliziusz.audiolibros.R.id.menu_acerca) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Mensaje de Acerca De");
            builder.setPositiveButton(android.R.string.ok, null);
            builder.create().show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void irUltimoVisitado() {
        SharedPreferences pref = getSharedPreferences(
                "com.example.audiolibros_internal", MODE_PRIVATE);
        int id = pref.getInt("ultimo", -1);
        if (id >= 0) {
            mostrarDetalle(id);
        } else {
            Toast.makeText(this,"Sin última vista",Toast.LENGTH_LONG).show();
        }
    }
    public void mostrarDetalle(int id) {
        DetalleFragment detalleFragment = (DetalleFragment)
                getSupportFragmentManager().findFragmentById(zombie.deliziusz.audiolibros.R.id.detalle_fragment);
        if (detalleFragment != null) {
            detalleFragment.ponInfoLibro(id);
        } else {
            DetalleFragment nuevoFragment = new DetalleFragment();
            Bundle args = new Bundle();
            args.putInt(DetalleFragment.ARG_ID_LIBRO, id);
            nuevoFragment.setArguments(args);
            FragmentTransaction transaccion = getSupportFragmentManager()
                    .beginTransaction();
            transaccion.replace(zombie.deliziusz.audiolibros.R.id.contenedor_pequeno, nuevoFragment);
            transaccion.addToBackStack(null);
            transaccion.commit();
        }
        SharedPreferences pref = getSharedPreferences(
                "com.example.audiolibros_internal", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("ultimo", id);
        editor.commit();
    }
    public void mostrarElementos(boolean mostrar) {
        appBarLayout.setExpanded(mostrar);
        toggle.setDrawerIndicatorEnabled(mostrar);
        if (mostrar) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            tabs.setVisibility(View.VISIBLE);
        } else {
            tabs.setVisibility(View.GONE);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }
    public void abrePreferencias() {
        int idContenedor = (findViewById(zombie.deliziusz.audiolibros.R.id.contenedor_pequeno) != null) ?
                zombie.deliziusz.audiolibros.R.id.contenedor_pequeno : zombie.deliziusz.audiolibros.R.id.contenedor_izquierdo;
        PreferenciasFragment prefFragment = new PreferenciasFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(idContenedor, prefFragment)
                .addToBackStack(null)
                .commit();
    }
}
