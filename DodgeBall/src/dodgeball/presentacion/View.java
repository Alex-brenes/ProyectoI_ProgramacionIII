// View.java 
// Autor: José Alexander Brenes Brenes
//        Juan Daniel Quirós
// Métodos para la visualización del juego
package dodgeball.presentacion;

import dodgeball.logic.Circunferencia;
import dodgeball.logic.Bola;
import dodgeball.logic.HUD;
import dodgeball.logic.Raqueta;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View extends javax.swing.JFrame implements java.util.Observer {

    private Model model;
    private Controller controller;
    java.awt.image.BufferedImage bf;
    dodgeball.logic.Musica fondo;

    public View() {
        super("Dodge Ball");
        fondo = new dodgeball.logic.Musica();
        fondo.reproducirMusica();
        bf = new java.awt.image.BufferedImage(WIDTH, HEIGHT, java.awt.image.BufferedImage.TYPE_INT_RGB);
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }

            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });
        try {
            circ = ImageIO.read(getClass().getResourceAsStream("imagenes/circ.png"));
            ballImage = ImageIO.read(getClass().getResourceAsStream("imagenes/ball30x30.gif"));
            background = ImageIO.read(getClass().getResourceAsStream("imagenes/background_590x600.jpg"));

        } catch (IOException ex) {

        }
        iniciarFrame();
    }

    private void iniciarFrame() {
        this.setContentPane(new JPanel() {
            @Override
            public void paint(java.awt.Graphics g) {
                g.drawImage(background, 0, 0, this);
                g.drawImage(circ,37,27,this);
            }
        });

        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar menu_bar = new JMenuBar();
        this.setJMenuBar(menu_bar);
        this.setLocationRelativeTo(null);

        JMenu file = new JMenu("File");
        JMenuItem settings = new JMenuItem("Settings");
        JMenu edit = new JMenu("Edit");
        edit.add(settings);
        JMenu about = new JMenu("About");
        JMenuItem aboutDB = new JMenuItem("DodgeBall");
        settings.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.pause();
                JTextField esferasOP = new JTextField(Integer.toString(model.getHud().getBolasRestantes() + model.getListaBolas().size()));
                JTextField velocidadOP = new JTextField(Integer.toString(model.getHud().getVelocidad()));
                Object[] message = {"Esferas:", esferasOP, "Velocidad:", velocidadOP};

                int entrada = javax.swing.JOptionPane.showConfirmDialog(null, message, "Settings", javax.swing.JOptionPane.OK_CANCEL_OPTION);

                if (entrada == 0) { //Si acepta
                    if (0 < Integer.parseInt(esferasOP.getText())) { //Si es un número positivo de bolas
                        if(Integer.parseInt(esferasOP.getText()) <= model.getHud().getTopeBolas()){
                            //Si disminuye la cantidad de bolas
                            if (model.getHud().getBolasRestantes() > Integer.parseInt(esferasOP.getText())
                                    || model.getListaBolas().size() > Integer.parseInt(esferasOP.getText())) { //
                                if(model.getListaBolas().size() > Integer.parseInt(esferasOP.getText())){
                                    model.eliminarBolas(Integer.parseInt(esferasOP.getText()));
                                }
                                if(model.getHud().getBolasRestantes() > Integer.parseInt(esferasOP.getText())){
                                    model.getHud().setBolasRestantes(Integer.parseInt(esferasOP.getText()) - model.getListaBolas().size());
                                }
                            }else{
                                model.getHud().setBolasRestantes(Integer.parseInt(esferasOP.getText()) - model.getListaBolas().size());
                            }

                            }
                    }
                    if (0 < Integer.parseInt(velocidadOP.getText())) { //Si es un número positivo
                        if (Integer.parseInt(velocidadOP.getText()) <= model.getHud().getTopeBolas() && Integer.parseInt(velocidadOP.getText()) > 2) {
                            model.getHud().setVelocidad(Integer.parseInt(velocidadOP.getText()));
                            controller.cambiarVelocidad(Integer.parseInt(velocidadOP.getText()));
                        }
                    }
                }
                controller.continuar();
            }
        });
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                System.out.println(model.getHud().getBolasRestantes());
                if (0 < model.getHud().getBolasRestantes()) {
                    model.agregarBola(e.getX(), e.getY());
                    model.getHud().reducirBolas();
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
        java.awt.Graphics g = bf.getGraphics();
        super.paint(g);
        this.dibujarModelo(model, g);
        graphics.drawImage(bf, 0, 0, null);
    }

    @Override
    public void update(java.awt.Graphics g) {
        paint(g);
    }

    @Override
    public void update(java.util.Observable sujeto, Object objeto) {
        for (Bola b : model.getListaBolas()) {
            switch(b.punto){
                case 0: break; //otro sonido
                case 1: this.gana.setFramePosition(0); this.gana.start(); this.model.getHud().aumentarPuntaje(); b.punto = 0; break;
                case 2: this.pierde.setFramePosition(0); this.pierde.start(); this.model.getHud().reducirPuntaje(); b.punto = 0; break;
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
        dibujarHUD(model.getHud(),graphics);
    }
    private void dibujarHUD(HUD hud,java.awt.Graphics graphics){
        graphics.setColor(Color.orange);
        graphics.setFont(new java.awt.Font("TimesRoman", java.awt.Font.PLAIN, 50));
        graphics.drawString(Integer.toString(hud.getPuntaje()), 470, 120);
        graphics.setFont(new java.awt.Font("TimesRoman", java.awt.Font.PLAIN, 20));
        graphics.setColor(Color.WHITE);
        graphics.drawString("Velocidad: ", 10, 550);
        graphics.drawString(Integer.toString(hud.getVelocidad()), 105, 550);
        graphics.drawString("Bolas restantes: ", 10, 580);
        graphics.drawString(Integer.toString(hud.getBolasRestantes()), 155, 580);
    }
    private void dibujarCircunferencia(Circunferencia circunferencia, java.awt.Graphics graphics) {
        int x = circunferencia.getCoordenada_x();
        int y = circunferencia.getCoordenada_y();
        int r = circunferencia.getRadio();
        graphics.setColor(java.awt.Color.BLACK);
        graphics.drawOval(circunferencia.getCoordenada_x(), circunferencia.getCoordenada_y(), circunferencia.getRadio() * 2, circunferencia.getRadio() * 2);

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

    }

    private Clip loadSound(String ruta) {
        try {
            javax.sound.sampled.AudioInputStream audioInputStream
                    = javax.sound.sampled.AudioSystem.getAudioInputStream(getClass().getResource(ruta));
            javax.sound.sampled.AudioFormat soundFormat = audioInputStream.getFormat();
            int soundSize = (int) (soundFormat.getFrameSize() * audioInputStream.getFrameLength());
            byte[] soundData = new byte[soundSize];
            DataLine.Info soundInfo = new DataLine.Info(Clip.class, soundFormat, soundSize);
            audioInputStream.read(soundData, 0, soundSize);
            Clip clip = (Clip) javax.sound.sampled.AudioSystem.getLine(soundInfo);
            clip.open(soundFormat, soundData, 0, soundSize);
            return clip;
        } catch (Exception e) {
            return null;
        }
    }

    private void dibujarBola(Bola bola, java.awt.Graphics graphics) {

        graphics.setColor(java.awt.Color.BLUE);
        graphics.drawLine(bola.getCoordenada_x(), bola.getCoordenada_y(), bola.getCoordenada_x() + bola.getRadio() * 2, bola.getCoordenada_y());
        graphics.drawLine(bola.getCoordenada_x(), bola.getCoordenada_y(), bola.getCoordenada_x(), bola.getCoordenada_y() + bola.getRadio() * 2);
        graphics.drawLine(bola.getCoordenada_x(), bola.getCoordenada_y() + bola.getRadio() * 2, bola.getCoordenada_x() + bola.getRadio() * 2, bola.getCoordenada_y() + bola.getRadio() * 2);
        graphics.drawLine(bola.getCoordenada_x() + bola.getRadio() * 2, bola.getCoordenada_y(), bola.getCoordenada_x() + bola.getRadio() * 2, bola.getCoordenada_y() + bola.getRadio() * 2);

        //graphics.drawLine(bola.getCoordenada_x() + bola.getRadio(), bola.getCoordenada_y() + bola.getRadio(), 295, 330);
        graphics.drawImage(ballImage, bola.getCoordenada_x(), bola.getCoordenada_y(), this);

    }

    private void dibujarRaqueta(Raqueta raqueta, java.awt.Graphics graphics) {
        graphics.setColor(java.awt.Color.white);
        graphics.fillRect(raqueta.getCoordenada_x(), raqueta.getCoordenada_y(), raqueta.getBase(), raqueta.getAltura());
    }
    //Dimensiones
    private final int WIDTH = 590;
    private final int HEIGHT = 590;
    //About
    private final String aboutInfo = "\t\tDodgeBall\nProgramación III. Proyecto I.\nAutores:\n\tJosé Alexander Brenes Brenes.\n\tJuan Daniel Quirós";

    //Imágenes
    Image ballImage;
    Image background;
    Image circ;
    //Sonidos
    private final javax.sound.sampled.Clip gana = this.loadSound("sonidos/gana.wav");
    private final javax.sound.sampled.Clip pierde = this.loadSound("sonidos/pierde.wav");


}
