package sv.devla.genesisapp.Reports;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;

import sv.devla.genesisapp.R;

public class SalesByDeptoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_by_depto);

        PieChart mPieChart = (PieChart) findViewById(R.id.piechart);

        mPieChart.addPieSlice(new PieModel("Ellas", 15, Color.parseColor("#FE6DA8")));
        mPieChart.addPieSlice(new PieModel("Ellos", 25, Color.parseColor("#56B7F1")));
        mPieChart.addPieSlice(new PieModel("Baby & Kids", 35, Color.parseColor("#CDA67F")));
        mPieChart.addPieSlice(new PieModel("Hogar", 9, Color.parseColor("#FED70E")));
        mPieChart.addPieSlice(new PieModel("Otro 1", 5, Color.parseColor("#000000")));
        mPieChart.addPieSlice(new PieModel("Otro 2", 11, Color.parseColor("#ffffff")));

        mPieChart.startAnimation();
    }
}
