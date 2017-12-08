package com.araceliteixeira.b2caccess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.araceliteixeira.b2caccess.DAO.ProductDAO;
import com.araceliteixeira.b2caccess.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductView extends AppCompatActivity {

    private boolean isRegisteredUser = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        Intent intent = getIntent();
        String email = (String) intent.getSerializableExtra("email");
        if (email != null && !email.isEmpty()) {
            isRegisteredUser = true;
            TextView user = (TextView) findViewById(R.id.product_user);
            user.setText(email);
        }

        setTitle(isRegisteredUser ? "PRODUCTS WITH DISCOUNT" : "PRODUCTS WITHOUT DISCOUNT");

        final List<Product> products = loadProducts();
        ListView productsList = (ListView) findViewById(R.id.products_list);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, products) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(products.get(position).getDescription());
                Double price = products.get(position).getPrice();
                if (isRegisteredUser) {
                    price -= price * (products.get(position).getDiscount() / 100.0);
                }
                text2.setText("Price: $" + price);
                return view;
            }
        };

        productsList.setAdapter(adapter);

        Button backButton = (Button) findViewById(R.id.product_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private List<Product> loadProducts() {
        ProductDAO dao = new ProductDAO(this);
        List<Product> products = dao.dbSearch();
        if (products == null || products.isEmpty()) {
            products = new ArrayList<>();

            Product p = new Product();
            p.setDescription("Smart TV 4K Philips");
            p.setPrice(600.0);
            p.setDiscount(15.0);
            products.add(p);

            p = new Product();
            p.setDescription("Smartphone Samsung Galaxy S8");
            p.setPrice(700.0);
            p.setDiscount(20.0);
            products.add(p);

            p = new Product();
            p.setDescription("Smartphone iPhone 8 Plus");
            p.setPrice(800.0);
            p.setDiscount(20.0);
            products.add(p);

            p = new Product();
            p.setDescription("Handbag Armani");
            p.setPrice(220.0);
            p.setDiscount(10.0);
            products.add(p);

            p = new Product();
            p.setDescription("Handbag Louis Vuitton");
            p.setPrice(1400.0);
            p.setDiscount(25.0);
            products.add(p);

            p = new Product();
            p.setDescription("Segway One S1");
            p.setPrice(600.0);
            p.setDiscount(20.0);
            products.add(p);

            p = new Product();
            p.setDescription("Mini Drone with Camera");
            p.setPrice(70.0);
            p.setDiscount(10.0);
            products.add(p);

            for (Product product : products) {
                dao.dbInsert(product);
            }
        }
        dao.close();
        return products;
    }
}
