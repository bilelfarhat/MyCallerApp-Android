package bilelfarhat.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import java.util.ArrayList;

public class List_Profil extends AppCompatActivity {

    RecyclerView rv;
    SearchView search;
    MyRecycleProfilAdapter adapter;
    ArrayList<Profil> originalData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_profil);
        search = findViewById(R.id.edSearch_list);
        rv = findViewById(R.id.rv);

        ProfilManager manager = new ProfilManager(List_Profil.this);
        manager.ouvrir();
        originalData = manager.getAllProfil();
        adapter = new MyRecycleProfilAdapter(List_Profil.this, originalData);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(List_Profil.this, 1, GridLayoutManager.VERTICAL, true);
        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(adapter);

        // Implement search functionality
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the original data based on the search query
                ArrayList<Profil> filteredList = filter(originalData, newText);
                // Update the RecyclerView adapter with the filtered data
                adapter.setData(filteredList);
                return true;
            }
        });
    }

    // Method to filter the original data based on the search query
    private ArrayList<Profil> filter(ArrayList<Profil> originalList, String query) {
        query = query.toLowerCase();
        ArrayList<Profil> filteredList = new ArrayList<>();
        for (Profil profil : originalList) {
            if (profil.name.toLowerCase().contains(query) ||
                    profil.lastName.toLowerCase().contains(query) ||
                    profil.number.toLowerCase().contains(query)) {
                filteredList.add(profil);
            }
        }
        return filteredList;
    }
}
