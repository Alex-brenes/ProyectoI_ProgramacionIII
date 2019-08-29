// View.java 
// Autor: José Alexander Brenes Brenes
//        Juan Daniel Quirós
// Métodos para la visualización del juego
package dodgeball;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View extends javax.swing.JFrame implements java.util.Observer {

    private Model model;
    private Controller controller;

    public View() {
        super("Dodge Ball");

        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }

            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });
        try {
            // Get Image

            ballImage = ImageIO.read(getClass().getResourceAsStream("imagenes/ball30x30.gif"));
//            circ = ImageIO.read(getClass().getResourceAsStream("imagenes/circbienrecortada.png"));
            background = ImageIO.read(getClass().getResourceAsStream("imagenes/background_590x600.jpg"));

        } catch (IOException ex) {

        }
        iniciarFrame();
    }

    private void iniciarFrame() {
        JPanel principal = new JPanel();
        JPanel panelMenu = new JPanel();
        JPanel panelJuego = new JPanel();
        principal.setBackground(Color.pink);
        this.add(principal);
        this.setLayout(null);
        principal.setLayout(null);
        panelJuego.setLayout(null);
        principal.setBounds(0, 0, WIDTH, HEIGHT);
//        panelJuego.setLayout(new java.awt.BorderLayout());
        panelJuego.setBackground(Color.cyan);
        panelJuego.setBounds(0, 0, WIDTH, HEIGHT);
        panelJuego.setOpaque(true);
        panelMenu.setBounds(0, 0, WIDTH, HEIGHT);
//        principal.add(panelJuego);
//        panelJuego.add(panelMenu);
        //
        this.setSize(WIDTH, HEIGHT);
//        panelMenu.setLayout(new java.awt.BorderLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.getContentPane().add(panelMenu);

        JMenuBar menu_bar = new JMenuBar();

//        panelMenu.add(menu_bar, java.awt.BorderLayout.NORTH);
        this.setLocationRelativeTo(null);
//
        this.setJMenuBar(menu_bar);
        JMenu file = new JMenu("File");
        JMenuItem settings = new JMenuItem("Settings");
        JMenu edit = new JMenu("Edit");
        edit.add(settings);
        JMenu about = new JMenu("About");
//
        JMenuItem aboutDB = new JMenuItem("DodgeBall");
        settings.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.pause();
                JTextField esferasOP = new JTextField(Integer.toString(bolas));
                JTextField velocidadOP = new JTextField(Integer.toString(velocidad));
                Object[] message = {"Esferas:", esferasOP, "Velocidad:", velocidadOP};

                int entrada = javax.swing.JOptionPane.showConfirmDialog(null, message, "Settings", javax.swing.JOptionPane.OK_CANCEL_OPTION);

                if (entrada == 0) {
                    if (0 < Integer.parseInt(esferasOP.getText())) { //Si es un numero positivo de bolas
                        if (bolas < Integer.parseInt(esferasOP.getText())) {//Si aumenta la cantidad de bolas
                            // model.agregarBolas(Integer.parseInt(esferasOP.getText()));

                        } else if (bolas > Integer.parseInt(esferasOP.getText())) { //Si disminuye
                            model.eliminarBolas(Integer.parseInt(esferasOP.getText()));
                        }
                        bolas = Integer.parseInt(esferasOP.getText());

                    }
                    if (0 < Integer.parseInt(velocidadOP.getText())) { //Si es un número positivo
                        if (velocidad <= TOPE) {
                            velocidad = Integer.parseInt(velocidadOP.getText());
                            controller.cambiarVelocidad(velocidad);
                        }
                    }

                }

                controller.continuar();

            }
        });
        principal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (model.getListaBolas().size() < bolas) {
                    model.agregarBola(e.getX(), e.getY());

                }
            }
        });
        aboutDB.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.pause();
                javax.swing.JOptionPane.showMessageDialog(null, aboutInfo, "About", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                controller.continuar();
            }

        });

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });
        about.add(aboutDB);
        file.add(exit);
        menu_bar.add(file);
        menu_bar.add(edit);
        menu_bar.add(about);
//        JLabel etiqueta = new JLabel("DodgeBall");

    }

    private void formKeyPressed(java.awt.event.KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_UP: {
                controller.mover(Model.ARR);
                break;
            }
            case KeyEvent.VK_DOWN: {
                controller.mover(Model.ABA);
                break;
            }
            case KeyEvent.VK_LEFT: {
                controller.mover(Model.IZQ);
                break;
            }
            case KeyEvent.VK_RIGHT: {
                controller.mover(Model.DER);
                break;
            }
        }
    }

    private void formKeyReleased(java.awt.event.KeyEvent evt) {
        int key = evt.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP: {
                controller.detenerHor();
            }
            case KeyEvent.VK_DOWN: {
                controller.detenerVer();
                break;
            }
            case KeyEvent.VK_LEFT: {
                controller.detenerHor();
            }
            case KeyEvent.VK_RIGHT: {
                controller.detenerHor();
                break;
            }
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void paint(java.awt.Graphics graphics) {
        if (dibujoAux == null || gAux == null) {
            dibujoAux = createImage(WIDTH, HEIGHT);
            gAux = dibujoAux.getGraphics();
        }
        this.dibujarModelo(model, gAux);
        graphics.drawImage(dibujoAux, 0, 0, this);
    }

    @Override
    public void update(java.awt.Graphics g) {
        paint(g);
    }

    @Override
    public void update(java.util.Observable sujeto, Object objeto) {
        for (Bola b : model.getListaBolas()) {
            if (b.punto != 0) {
                if (b.punto == 1) {
                    puntaje++;
                } else if (b.punto == 2) {
                    puntaje--;
                }

                b.punto = 0;
            }
        }
        this.repaint();

    }

    public void dibujarModelo(Model model, java.awt.Graphics graphics) {
        dibujarCircunferencia(model.getCircunferencia(), graphics);
        for (Bola b : model.getListaBolas()) {
            dibujarBola(b, graphics);
        }
        dibujarRaqueta(model.getRaqueta(), graphics);
    }

    private void dibujarCircunferencia(Circunferencia circunferencia, java.awt.Graphics graphics) {
        int x = circunferencia.getCoordenada_x();
        int y = circunferencia.getCoordenada_y();
        int r = circunferencia.getRadio();
        graphics.setColor(java.awt.Color.BLACK);
        graphics.drawOval(circunferencia.getCoordenada_x()/*WIDTH/2 - circunferencia.getRadio()*/, circunferencia.getCoordenada_y()/*HEIGHT/2 - circunferencia.getRadio()*/, circunferencia.getRadio() * 2, circunferencia.getRadio() * 2);
        graphics.setColor(java.awt.Color.RED);
        graphics.drawLine(x + r, y, x + r, y + 2 * r);
        graphics.drawLine(x, y + r, x + 2 * r, y + r);
        graphics.drawImage(background, 0, 0, this);
        //graphics.drawImage(circ, x, y, this);
        //Para el ancho de los arcos
        Graphics2D arcos = (Graphics2D) (graphics);
        arcos.setStroke(new BasicStroke(4));
        //Segmento I
        graphics.setColor(java.awt.Color.green);
        graphics.drawArc(x, y, 500, 500, 0, 15);
        graphics.setColor(java.awt.Color.RED);
        graphics.drawArc(x, y, 500, 500, 30, 30);
        graphics.setColor(java.awt.Color.green);
        arcos.drawArc(x, y, 500, 500, 75, 15);
        //Segmento II
        arcos.drawArc(x, y, 500, 500, 90, 15);
        graphics.setColor(java.awt.Color.RED);
        arcos.drawArc(x, y, 500, 500, 120, 30);
        graphics.setColor(java.awt.Color.green);
        arcos.drawArc(x, y, 500, 500, 165, 15);
        //Segmento III
        arcos.drawArc(x, y, 500, 500, 180, 15);
        graphics.setColor(java.awt.Color.RED);
        arcos.drawArc(x, y, 500, 500, 210, 30);
        graphics.setColor(java.awt.Color.green);
        arcos.drawArc(x, y, 500, 500, 255, 15);
        //Segmento IV
        arcos.drawArc(x, y, 500, 500, 270, 15);
        graphics.setColor(java.awt.Color.RED);
        arcos.drawArc(x, y, 500, 500, 300, 30);
        graphics.setColor(java.awt.Color.green);
        arcos.drawArc(x, y, 500, 500, 345, 15);
        //Se pone otra vez el ancho
        arcos.setStroke(new BasicStroke(1));
        arcos.setColor(Color.orange);
        graphics.setFont(new java.awt.Font("TimesRoman", java.awt.Font.PLAIN, 50));

        graphics.drawString(Integer.toString(puntaje), 500, 120);
    }

    private void dibujarBola(Bola bola, java.awt.Graphics graphics) {
//        graphics.setColor(java.awt.Color.PINK);
////        graphics.fillOval(bola.getCoordenada_x(), bola.getCoordenada_y(), bola.getRadio() * 2, bola.getRadio() * 2);
        graphics.setColor(java.awt.Color.BLUE);
        graphics.drawLine(bola.getCoordenada_x(), bola.getCoordenada_y(), bola.getCoordenada_x() + bola.getRadio() * 2, bola.getCoordenada_y());
        graphics.drawLine(bola.getCoordenada_x(), bola.getCoordenada_y(), bola.getCoordenada_x(), bola.getCoordenada_y() + bola.getRadio() * 2);
        graphics.drawLine(bola.getCoordenada_x(), bola.getCoordenada_y() + bola.getRadio() * 2, bola.getCoordenada_x() + bola.getRadio() * 2, bola.getCoordenada_y() + bola.getRadio() * 2);
        graphics.drawLine(bola.getCoordenada_x() + bola.getRadio() * 2, bola.getCoordenada_y(), bola.getCoordenada_x() + bola.getRadio() * 2, bola.getCoordenada_y() + bola.getRadio() * 2);

        graphics.drawLine(bola.getCoordenada_x() + bola.getRadio(), bola.getCoordenada_y() + bola.getRadio(), 295, 330);
        graphics.drawImage(ballImage, bola.getCoordenada_x(), bola.getCoordenada_y(), this);

    }

    private void dibujarRaqueta(Raqueta raqueta, java.awt.Graphics graphics) {
        graphics.setColor(java.awt.Color.magenta);
        graphics.fillRect(raqueta.getCoordenada_x(), raqueta.getCoordenada_y(), raqueta.getBase(), raqueta.getAltura());
    }

    private final int WIDTH = 590;
    private final int HEIGHT = 590;
    private final String settingsOP = "Esferas:";
    private final String aboutInfo = "\t\tDodgeBall\nProgramación III. Proyecto I.\nAutores:\n\tJosé Alexander Brenes Brenes.\n\tJuan Daniel Quirós";
    private int puntaje = 0;
    private int bolas = 1;
    private int velocidad = 7;
    Image ballImage;
    Image background;
    Image circ;
    private final int TOPE = 15;
    //PRUEBA QUITAR PARPADEO
    private Image dibujoAux;
    private java.awt.Graphics gAux;

}
