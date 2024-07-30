package kr.co.example.layouttemplate;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void attachLinearLayoutVertical(View view) {

        View linearLayout = get_LLV_View();

        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        frameLayout.removeAllViews();
        frameLayout.addView(linearLayout);
    }

    public View get_LLV_View() { // 그림 파일, 커피 이름, 가격

        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.menu_item,null, false);

        ImageView imageView = linearLayout.findViewById(R.id.imageView);
        TextView textView= linearLayout.findViewById(R.id.textView);
        TextView textView2= linearLayout.findViewById(R.id.textView2);

        imageView.setImageResource(R.drawable.coffee);
        textView.setText("Coffee");
        textView2.setText("3000");

        return linearLayout;
    }

    public void attachGridLayout(View view) {

        View gridLayout = get_GL_View();

        View menuigem1 = get_LLV_View();
        View menuigem2 = get_LLV_View();
        View menuigem3 = get_LLV_View();
        View menuigem4 = get_LLV_View();
        View menuigem5 = get_LLV_View();

        ((GridLayout)gridLayout).addView(menuigem1);
        ((GridLayout)gridLayout).addView(menuigem2);
        ((GridLayout)gridLayout).addView(menuigem3);
        ((GridLayout)gridLayout).addView(menuigem4);
        ((GridLayout)gridLayout).addView(menuigem5);

        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        frameLayout.removeAllViews();
        frameLayout.addView(gridLayout);
    }

    public View get_GL_View() {
        GridLayout gridLayout = (GridLayout) getLayoutInflater().inflate(R.layout.menu_panel,null, false);

        return gridLayout;
    }

    public void attachTableRow(View view) {
        View tableRow1 = get_TR_View("아메리카노", 1, 3000);
        View tableRow2 = get_TR_View("카페라떼", 1, 3500);
        View tableRow3 = get_TR_View("에스프레소", 1, 2500);

        TableLayout tableLayout = new TableLayout(this);
        tableLayout.addView(tableRow1);
        tableLayout.addView(tableRow2);
        tableLayout.addView(tableRow3);

        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        frameLayout.removeAllViews();
        frameLayout.addView(tableLayout);
    }

    public View get_TR_View(String item, int cnt, int price) {
        TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.order_item, null, false);

        TextView itemOrdered = tableRow.findViewById(R.id.itemOrdered);
        Button btnDecItem = tableRow.findViewById(R.id.btnDecItem);
        TextView itemCnt = tableRow.findViewById(R.id.itemCnt);
        Button btnIncItem = tableRow.findViewById(R.id.btnIncItem);
        TextView priceOrdered = tableRow.findViewById(R.id.priceOrdered);
        Button btnCancelItem = tableRow.findViewById(R.id.btnCancelItem);
        if(cnt==1) btnDecItem.setEnabled(false);

        itemOrdered.setText(item);
        itemCnt.setText(""+cnt);
        priceOrdered.setText(""+price);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableRow tableRow = (TableRow) v.getParent();
                if(v==tableRow.findViewById(R.id.btnDecItem)) { //)getChildAt(1)) { // 빼기 버튼
                    TextView itemCnt = tableRow.findViewById(R.id.itemCnt);
                    int cnt = Integer.parseInt(itemCnt.getText().toString());
                    if(cnt>1) {
                        cnt--;
                        itemCnt.setText(""+cnt);
                        if(cnt==1) btnDecItem.setEnabled(false);
                    }
                } else if(v==tableRow.findViewById(R.id.btnIncItem)) { //tableRow.getChildAt(3)) { // 더하기 버튼
                    TextView itemCnt = tableRow.findViewById(R.id.itemCnt);
                    int cnt = Integer.parseInt(itemCnt.getText().toString());
                    cnt++;
                    itemCnt.setText(""+cnt);
                    Button btnDecItem = tableRow.findViewById(R.id.btnDecItem);
                    btnDecItem.setEnabled(true);
                } else if(v==tableRow.findViewById(R.id.btnCancelItem)) { //getChildAt(5)) { // 취소 버튼
                    TableLayout tableLayout = (TableLayout) tableRow.getParent();
                    tableLayout.removeView(tableRow);
                }
            }
        };
        btnDecItem.setOnClickListener(listener);
        btnIncItem.setOnClickListener(listener);
        btnCancelItem.setOnClickListener(listener);

        return tableRow;
    }
}