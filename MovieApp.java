import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class MovieApp extends JFrame {
    private JComboBox<String> movieList;
    private JTextPane movieInfoArea;
    private JLabel movieImage;

    public MovieApp(MovieData movieData) {
        super("Справочник мультфильмов и кино");

        Map<String, Object[]> movieInfo = movieData.getMovieInfo();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 600);
        setLayout(new FlowLayout());
        setMinimumSize(new Dimension(1400, 600));
        setMaximumSize(new Dimension(1500, 600));

        movieList = new JComboBox<>(movieInfo.keySet().toArray(new String[0]));
        add(movieList);

        movieInfoArea = new JTextPane();
        add(movieInfoArea);
        movieInfoArea.setFont(new Font("Arial", Font.ITALIC, 14));
        movieInfoArea.setPreferredSize(new Dimension(300, 300)); // Устанавливаем начальные размеры JTextPane
        movieInfoArea.setMinimumSize(new Dimension(300, 100)); // Минимальный размер JTextPane
        movieInfoArea.setMaximumSize(new Dimension(600, 600)); // Максимальный размер JTextPane


        movieImage = new JLabel();
        add(movieImage);

        movieList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMovie = (String) movieList.getSelectedItem();
                Object[] movieData = movieInfo.get(selectedMovie);
                String info = (String) movieData[0];
                String imagePath = (String) movieData[1];

                movieInfoArea.setText(info);
                if (imagePath != null) {
                    ImageIcon image = new ImageIcon(imagePath);
                    movieImage.setIcon(image);
                } else {
                    movieImage.setIcon(null);
                }
            }
        });
    }

    public static void main(String[] args) {
        MovieData movieData = new MovieInfo();
        new MovieApp(movieData).setVisible(true);
    }
}