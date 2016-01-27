package com.example.android.producerconsumer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recent_tuple)
    TextView recentTupleTextView;

    @Bind(R.id.queue_size)
    TextView queueSizeTextView;

    public PriorityBlockingQueue<Tuple> queue = new PriorityBlockingQueue<>(
            4, new Comparator<Tuple>() {
        @Override
        public int compare(Tuple lhs, Tuple rhs) {
            return lhs.compareTo(rhs) * -1;
        }
    });

    public Consumer consumer = new UnreliableConsumer(queue, 20);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        new ProducerThread().start();
        new ConsumerThread().start();
    }

    public void updateQueueSize() {
        String queueSizeString = getString(R.string.queue_size, queue.size());
        queueSizeTextView.setText(queueSizeString);
    }

    public void updateRecentTuple(@NonNull Tuple tuple) {
        Log.i(MainActivity.class.getName(), "Recent = " + tuple);
        String recentTupleString = getString(R.string.recent_tuple, tuple.name);
        recentTupleTextView.setText(recentTupleString);
    }

    class ProducerThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                Tuple tuple = Producer.getTuple();
                queue.put(tuple);
                try {
                    Thread.sleep(Randoms.getInteger(400, 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class ConsumerThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                Log.d(MainActivity.class.getName(), "Queue = " + queue.size() + " >>> " + queue.toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateQueueSize();
                    }
                });

                boolean consumed;
                try {
                    consumed = consumer.consume();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    Tuple tuple = consumer.getTuple();
                    Log.e(MainActivity.class.getName(), "Putting " + tuple + " back to the queue.");
                    queue.put(tuple);
                    consumed = false;
                }
                if (consumed) {
                    final Tuple tuple = consumer.getTuple();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateRecentTuple(tuple);
                        }
                    });
                }
                try {
                    Thread.sleep(Randoms.getInteger(600, 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
