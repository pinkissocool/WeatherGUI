import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


public class WeatherGUI implements ActionListener{

    //Where weather info will be shown such as temp and weather conditions
    private JTextArea weatherInfo;

    //Where user will write zip code
    private JTextField zipCodeField;

    //Weather API
    private WeatherNetworkingClient client;

    private String zip;

    public WeatherGUI(){
        weatherInfo = new JTextArea(20, 35);
        zipCodeField = new JTextField();
        client = new WeatherNetworkingClient();
        zip = "";

        setupGUI();

    }

    private void setupGUI(){
        JFrame frame = new JFrame("Weather App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //top panel
        JLabel welcomeLabel = new JLabel("   Current Weather   ");
        welcomeLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.pink);

        JPanel weatherListPanel = new JPanel();
        weatherInfo.setText("temp loading...");
        weatherInfo.setFont(new Font("Helvetica", Font.PLAIN, 16));
        weatherInfo.setWrapStyleWord(true);
        weatherInfo.setLineWrap(true);
        weatherListPanel.add(weatherInfo);

        JPanel entryPanel = new JPanel(); // the panel is not visible in output
        JLabel weatherLabel = new JLabel("|Enter Zip Code: ");
        zipCodeField = new JTextField(10); // accepts up to 10 characters
        JButton submitButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");
        entryPanel.add(weatherLabel);
        entryPanel.add(zipCodeField);
        entryPanel.add(submitButton);
        entryPanel.add(clearButton);

        frame.add(welcomeLabel, BorderLayout.NORTH);
        frame.add(weatherListPanel, BorderLayout.CENTER);
        frame.add(entryPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(this);
        clearButton.addActionListener(this);

        frame.pack();
        frame.setVisible(true);
    }

    private void loadWeatherInfo(){
        double temperature = client.getTemperature();
        weatherInfo.setText("Temperature: " + temperature);
    }

    //implements ActionListener
    public void actionPerformed(ActionEvent e){
        //might have to change this for checkbox
        JButton button = (JButton) (e.getSource());  // cast source to JButton

        String text = button.getText();

        if (text.equals("Submit")){
            String zipCode = zipCodeField.getText();
            zip = zipCode;
            loadWeatherInfo();
        }
        else if (text.equals("Clear")){
            zipCodeField.setText("");
        }


    }
}