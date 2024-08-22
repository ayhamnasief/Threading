package com.example.threading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button btn1, btn2, btn3, btn4, btn5, btn6;
TextView tv3, tv4, tv5, tv6;
    MyHadler myHadler = new MyHadler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        tv3 = findViewById(R.id.tv3);
        btn4 = findViewById(R.id.btn4);
        tv4 = findViewById(R.id.tv4);
        btn5 = findViewById(R.id.btn5);
        tv5 = findViewById(R.id.tv5);
        btn6 = findViewById(R.id.btn6);
        tv6 = findViewById(R.id.tv6);

        // Deal with UI
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            int finalI = i;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "Counter: "+ finalI, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
                t2.start();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t3 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            int finalI = i;
                            tv3.post(new Runnable() {
                                @Override
                                public void run() {
                                    tv3.setText("Counter: "+ finalI);
                                }
                            });
                        }
                    }
                });
                t3.start();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t4 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            int finalI = i;
                            tv4.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tv4.setText("Counter: "+ finalI);
                                }
                            }, 2000);
                        }
                    }
                });
                t4.start();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t5 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            Message msg = new Message();
                            msg.arg1 = i;
                            myHadler.sendMessage(msg);
                            // myHadler.sendEmptyMessage(i);
                        }
                    }
                });
                t5.start();
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t6 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            int finalI = i;
                            myHadler.post(new Runnable() {
                                @Override
                                public void run() {
                                    tv6.setText(String.valueOf("Counter: "+finalI));
                                }
                            });
                        }
                    }
                });
                t6.start();
            }
        });

        // Create worker thread
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(MainActivity.this, "Sleeping", Toast.LENGTH_SHORT).show();
                    Thread.sleep(5000);
                    Toast.makeText(MainActivity.this, "Awake", Toast.LENGTH_SHORT).show();

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


    }

    // We can make it static to let everyone deal with it
    class MyHadler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            tv5.setText("Counter: "+String.valueOf(msg.arg1));
            // tv5.setText(String.valueOf(msg.what)); in case myHadler.sendEmptyMessage(i);
        }
    }
}