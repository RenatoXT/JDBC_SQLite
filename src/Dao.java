import java.sql.*;
import java.io.File;
import java.nio.file.*;

public class Dao {
    private Connection connect() {
        File mainPath = new File("App");
        Path path = Paths.get(mainPath.getAbsolutePath());
        Path projectRoot = path.getParent().getParent();
        Path dbPath = Paths.get(projectRoot + "/db/sample_db.db");
        String url = "jdbc:sqlite:" + dbPath;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insert(String albumName, int artistId) {
        String sql = "INSERT INTO albums(Title,ArtistId) VALUES(?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, albumName);
            pstmt.setDouble(2, artistId);
            pstmt.executeUpdate();
            System.out.println("Inserted!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(int AlbumId, String albumTitle, int artistId) {
        String sql = "UPDATE albums SET Title = ? , "
                + "ArtistId = ? "
                + "WHERE AlbumId = ?";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, albumTitle);
            pstmt.setDouble(2, artistId);
            pstmt.setInt(3, AlbumId);

            pstmt.executeUpdate();
            System.out.println("Updated!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM albums WHERE AlbumId = ?";

        try (
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ){
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            System.out.println("Deleted!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void select() {
        Statement stmt = null;
        String query = "SELECT * FROM albums";
        try {
            Connection conn = this.connect();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("title");
                String id = rs.getString("AlbumId");
                System.out.println("Id: " + id.toString() + " -> Title: " + name);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
