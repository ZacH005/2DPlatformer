//import org.
//import org.json.JSONObject;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MapLoader {
//    public static List<Platform> loadMap(String mapFilePath) {
//        List<Platform> platforms = new ArrayList<>();
//        try {
//            String content = new String(Files.readAllBytes(Paths.get(mapFilePath)));
//            JSONObject mapData = new JSONObject(content);
//            JSONArray platformArray = mapData.getJSONArray("platforms");
//
//            for (int i = 0; i < platformArray.length(); i++) {
//                JSONObject platformData = platformArray.getJSONObject(i);
//                int x = platformData.getInt("x");
//                int y = platformData.getInt("y");
//                int width = platformData.getInt("width");
//                int height = platformData.getInt("height");
//                platforms.add(new Platform(x, y, width, height));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return platforms;
//    }
//}
