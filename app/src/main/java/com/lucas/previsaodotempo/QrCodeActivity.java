package com.lucas.previsaodotempo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

public class QrCodeActivity extends AppCompatActivity {

    private CompoundBarcodeView barcodeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        barcodeView = findViewById(R.id.barcode_scanner);

        // Inicia a leitura contínua do QR
        barcodeView.decodeContinuous(callback);
    }

    private final BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if (result.getText() != null && !result.getText().isEmpty()) {
                String cidade = result.getText().trim();

                Toast.makeText(QrCodeActivity.this, "Cidade lida: " + cidade, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.putExtra("cidade", cidade);
                setResult(RESULT_OK, intent);

                barcodeView.pause();
                finish();
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        barcodeView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeView.pause();
    }
}
