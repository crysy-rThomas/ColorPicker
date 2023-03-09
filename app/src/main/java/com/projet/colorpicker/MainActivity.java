package com.projet.colorpicker;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, TextWatcher {

    private static final int CAMERA_CAPTURE = 1;
    private static final int CROP_IMAGE_ACTIVITY_REQUEST_CODE = 2;
    private SeekBar red,green,blue;
    private SurfaceView box;
    private EditText code,tRed,tBlue,tGreen;
    private TextView text;

    private ImageView img;
    private Bitmap bimap;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        red = findViewById(R.id.red);
        blue = findViewById(R.id.blue);
        green = findViewById(R.id.green);
        box = findViewById(R.id.box);
        code = findViewById(R.id.code);
        text = findViewById(R.id.text1);
        tRed = findViewById(R.id.NbRed);
        tGreen = findViewById(R.id.nbGreen);
        tBlue = findViewById(R.id.NbBlue);


        red.setOnSeekBarChangeListener(this);
        blue.setOnSeekBarChangeListener(this);
        green.setOnSeekBarChangeListener(this);

        code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                code.setSelection(code.getText().length());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                code.setSelection(code.getText().length());
                if (charSequence.length() >= 3) {
                    String str = "";
                    str += charSequence.charAt(1);
                    str += charSequence.charAt(2);

                    int decim = Integer.parseInt(str, 16);
                    red.setProgress(decim);
                }
                if (charSequence.length() >= 5) {
                    String str = "";
                    str += charSequence.charAt(3);
                    str += charSequence.charAt(4);

                    int decim = Integer.parseInt(str, 16);
                    green.setProgress(decim);
                }
                if (charSequence.length() >= 7) {
                    String str = "";
                    str += charSequence.charAt(5);
                    str += charSequence.charAt(6);

                    int decim = Integer.parseInt(str, 16);
                    blue.setProgress(decim);
                }

            }


            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        tRed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (tRed.getText().toString().equals("")) {
                    tRed.setText(0);
                    tRed.setSelection(tRed.getText().length());

                }
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                if (tRed.getText().length() > 0) {
                    if (Integer.parseInt(tRed.getText().toString()) > 255) {
                        red.setProgress(255, true);
                        tRed.setSelection(tRed.getText().length());

                    } else {
                        red.setProgress(Integer.parseInt(tRed.getText().toString()), true);
                        tRed.setSelection(tRed.getText().length());

                    }
                } else {
                    red.setProgress(0, true);
                    tRed.setSelection(tRed.getText().length());

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        tGreen.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (tGreen.getText().toString().equals("")) {
                    tGreen.setText(0);
                    tGreen.setSelection(tGreen.getText().length());

                }
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                if (tGreen.getText().length() > 0) {
                    if (Integer.parseInt(tGreen.getText().toString()) > 255) {
                        green.setProgress(255, true);
                        tGreen.setSelection(tGreen.getText().length());
                    } else {
                        green.setProgress(Integer.parseInt(tGreen.getText().toString()), true);
                        tGreen.setSelection(tGreen.getText().length());
                    }
                } else {
                    green.setProgress(0, true);
                    tGreen.setSelection(tGreen.getText().length());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        tBlue.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (tBlue.getText().toString().equals("")) {
                    tBlue.setText(0);
                    tBlue.setSelection(tBlue.getText().length());

                }
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                if (tBlue.getText().length() > 0) {
                    if (Integer.parseInt(tBlue.getText().toString()) > 255) {
                        blue.setProgress(255, true);
                        tBlue.setSelection(tBlue.getText().length());

                    } else {
                        blue.setProgress(Integer.parseInt(tBlue.getText().toString()), true);
                        tBlue.setSelection(tBlue.getText().length());

                    }
                } else {
                    blue.setProgress(0, true);
                    tBlue.setSelection(tBlue.getText().length());

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Button btnCam = findViewById(R.id.button_cam);

        btnCam.setOnClickListener(view -> {
            try{
                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(captureIntent, CAMERA_CAPTURE);

            } catch (ActivityNotFoundException anfe) {
                Context context = getApplicationContext();
                CharSequence errorMessage = "Whoops - your device doesn't support capturing images!";
                Toast toast = Toast.makeText(context,errorMessage,Toast.LENGTH_SHORT);
                toast.show();
            }
        });




        /*  Show Captured over ImageView  */
        img = findViewById(R.id.imageView);
        img.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE) {

                ImageView imageView = (img);
                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();


                // Get the dimensions of the original bitmap
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();

                int pixelc = bitmap.getPixel(width / 2,height / 2);
                int pixelhg = bitmap.getPixel((width / 2)-1,(height / 2)-1);
                int pixelhc = bitmap.getPixel((width / 2),(height / 2)-1);
                int pixelhd = bitmap.getPixel((width / 2)+1,(height / 2)-1);
                int pixelcd = bitmap.getPixel((width / 2)-1,(height / 2));
                int pixelcg = bitmap.getPixel((width / 2)+1,(height / 2));
                int pixelbg = bitmap.getPixel((width / 2)-1,(height / 2)+1);
                int pixelbc = bitmap.getPixel((width / 2),(height / 2)+1);
                int pixelbd = bitmap.getPixel((width / 2)+1,(height / 2)+1);

                int[] tableP = {pixelc,pixelhg,pixelhc,pixelhd,pixelcd,pixelcg,pixelbg,pixelbc,pixelbd};

                int sumRed = 0;
                int sumGreen = 0;
                int sumBleu = 0;
                for (int i = 0; i < tableP.length; i++){
                    sumRed += Color.red(tableP[i]);
                    sumGreen += Color.green(tableP[i]);
                    sumBleu += Color.blue(tableP[i]);
                }


                int avg_r = sumRed / tableP.length;
                int avg_g = sumGreen / tableP.length;
                int avg_b = sumBleu / tableP.length;

                red.setProgress(avg_r);
                green.setProgress(avg_g);
                blue.setProgress(avg_b);

                ChooseColor c = new ChooseColor();
                int re = red.getProgress();
                int v = green.getProgress();
                int bl = blue.getProgress();
                int rgb = c.createRGB(re,v,bl);

                code.setText(c.codeHTML(rgb));

            }
            return true;
        });
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        ChooseColor c = new ChooseColor();
        int r= red.getProgress();
        int v = green.getProgress();
        int bl = blue.getProgress();
        int rgb = c.createRGB(r,v,bl);

        if(b) {
            code.setText(c.codeHTML(rgb));
        }


        box.setBackgroundColor(rgb);
        text.setTextColor(rgb);



        tRed.setText(String.valueOf(r));
        tGreen.setText(String.valueOf(v));
        tBlue.setText(String.valueOf(bl));


    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //user is returning from capturing an image using the camera
        if(requestCode == CAMERA_CAPTURE){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(bitmap);

            Uri imageUri = getImageUri(getBaseContext(), bitmap);

            CropImage.activity(imageUri)
                    .start(this);

        }

        //user is returning from cropping the image
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            Uri resultUri = result.getUri();
            img.setImageURI(resultUri);


        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}
