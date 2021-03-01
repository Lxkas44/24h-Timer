import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JToolTip;

public class TimDerTimer

{
    private int m = 0;
    private int s = 0;
    private int st = 0;
    private int delay = 1000;
    JLabel stunde = new JLabel("Stunde: 0");
    JLabel sek = new JLabel("Sekunde: 0");
    JLabel min = new JLabel("Minute: 0");
    JButton start = new JButton("Start");
    JButton pause = new JButton("Pausieren");
    JButton stopp = new JButton("Stopp");
    Timer timer = new Timer(delay,null);
    int pausiert = 0;
    public TimDerTimer(){
        JFrame frame = new JFrame("Tim der Timer");

        JMenuBar jmb = new JMenuBar();
        frame.setJMenuBar(jmb);

        JMenu delayChanger = new JMenu("Geschwindigkeit");
        delayChanger.setToolTipText("Zeigt Geschwindigtkeits Optionen");
        jmb.add(delayChanger);

        JMenuItem nullfünf = new JMenuItem("0,5x");
        nullfünf.setToolTipText("Halbiert die Geschwindikeit");
        delayChanger.add(nullfünf);

        JMenuItem eins = new JMenuItem("1x");
        eins.setToolTipText("Normalisiert die Geschwindigkeit");
        delayChanger.add(eins);

        JMenuItem zwei = new JMenuItem("2x");
        zwei.setToolTipText("Verdoppelt die Geschwindigkeit");
        delayChanger.add(zwei);



        JMenu plus = new JMenu("Vorspulen");
        plus.setToolTipText("Fügt Zeit hinzu");
        jmb.add(plus);

        JMenuItem pluszehns = new JMenuItem("+10s");
        pluszehns.setToolTipText("Fügt 10s hinzu");
        plus.add(pluszehns);

        JMenuItem pluszehnmin = new JMenuItem("+10min");
        pluszehnmin.setToolTipText("Fügt 10min hinzu");
        plus.add(pluszehnmin);

        JMenuItem pluseinh = new JMenuItem("+1h");
        pluseinh.setToolTipText("Fügt 1h hinzu");
        plus.add(pluseinh);

        pluszehns.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                s = s + 10;
                sek.setText("Sekunde: " + s);
            }

        });

        pluszehnmin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                m = m + 10;
                min.setText("Minute: " + m);
            }

        });

        pluseinh.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                st = st + 1;
                stunde.setText("Stunde: "+st);
            }

        });


        JMenu minus = new JMenu("Zurückspulen");
        minus.setToolTipText("Zieht Zeit ab");
        jmb.add(minus);

        JMenuItem minuszehns = new JMenuItem("-10s");
        minuszehns.setToolTipText("Zieht 10s ab");
        minus.add(minuszehns);

        JMenuItem minuszehnmin = new JMenuItem("-10min");
        minuszehnmin.setToolTipText("Zieht 10min ab");
        minus.add(minuszehnmin);

        JMenuItem minuseinh = new JMenuItem("-1h");
        minuseinh.setToolTipText("Zieht 1h ab");
        minus.add(minuseinh);

        minuszehns.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                s = s - 10;
                sek.setText("Sekunde: " + s);
            }

        });

        minuszehnmin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                m = m - 10;
                min.setText("Minute: " + m);
            }

        });

        minuseinh.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                st = st - 1;
                stunde.setText("Stunde: "+st);
            }

        });






        JPanel panel = new JPanel(new GridLayout(2,2));
        frame.add(panel);
        panel.add(stunde);
        stunde.setToolTipText("Zeigt die vergangen Stunden");
        panel.add(min);
        min.setToolTipText("Zeigt die vergangen Minuten");
        panel.add(sek);
        sek.setToolTipText("Zeigt die vergangen Sekunden");
        panel.add(start);
        start.setToolTipText("Startet den Timer");
        panel.add(pause);
        pause.setToolTipText("Pausiert den Timer");
        panel.add(stopp);
        stopp.setToolTipText("Stoppt den Timer");
        stopp.setEnabled(false);
        pause.setEnabled(false);

        start.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                start();
            }

        });

        stopp.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                stopp();
            }

        });

        pause.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                pausiere();
            }

        });

        nullfünf.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                timer.setDelay(2000);
            }

        });

        eins.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                timer.setDelay(1000);
            }

        });

        zwei.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                timer.setDelay(500);
            }

        });

        timer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(s < 59){
                    s++;
                    sek.setText("Sekunde: "+s);
                }
                else if(m < 59) {
                    s = 0;
                    m++;
                    min.setText("Minute: "+m);
                    sek.setText("Sekunde: "+s);
                }
                else if(st < 23){
                    s = 0;
                    m = 0;
                    st++;
                    min.setText("Minute: "+m);
                    sek.setText("Sekunde: "+s);
                    stunde.setText("Stunde: "+st);
                }
                else{
                    timer.stop();
                    s = 0;
                    m = 0;
                    st = 0;
                    min.setText("Minute: "+m);
                    sek.setText("Sekunde: "+s);
                    stunde.setText("Stunde: "+st);
                    start.setEnabled(true);
                    stopp.setEnabled(false);

                    JFrame warnung = new JFrame("Warnung");
                    JPanel wpanel = new JPanel(new GridLayout(0,1));
                    warnung.add(wpanel);
                    JLabel wtext = new JLabel("Warnung: Zeitlimit überschritten!");
                    wpanel.add(wtext);
                    JButton ok = new JButton("OK");
                    ok.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            warnung.dispose();
                        }
                    });
                    wpanel.add(ok);

                    warnung.pack();
                    warnung.setVisible(true);
                }
            }
        });

        frame.setPreferredSize(new Dimension(300, 150));
        frame.pack();
        frame.setVisible(true);
    }

    public void start(){
        timer.start();
        start.setEnabled(false);
        stopp.setEnabled(true);
        pause.setEnabled(true);
    }

    public void stopp(){
        timer.stop();
        s = 0;
        m = 0;
        st = 0;
        min.setText("Minute: "+m);
        sek.setText("Sekunde: "+s);
        stunde.setText("Stunde: "+st);
        start.setEnabled(true);
        stopp.setEnabled(false);
        pause.setEnabled(false);
        pause.setText("Pausieren");
        pausiert = 0;
        pause.setToolTipText("Pausiert den Timer");
    }

    public void pausiere(){

        switch(pausiert){
            case 0:
                timer.stop();
                pause.setText("Fortsetzen");
                pausiert = 1;
                pause.setEnabled(true);
                pause.setToolTipText("Startet den Timer wieder");
                break;
            case 1:
                timer.start();
                pause.setText("Pausieren");
                pausiert = 0;
                pause.setToolTipText("Pausiert den Timer");
                break;

        }

    }

    public static void main(String[] args) {
        new TimDerTimer();
    }
}
