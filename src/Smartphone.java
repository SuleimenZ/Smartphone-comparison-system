import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class Smartphone {
    private String name;
    private String brand;
    private LocalDate releaseDate;
    private Double weight;
    private Integer resolutionX;
    private Integer resolutionY;
    private Double depth;
    private Double width;
    private Double height;
    private Integer storage;
    private Integer RAM;
    private String chipset;
    private String color;
    private Integer battery;

    public Smartphone(String path) throws FileNotFoundException
    {
        File phoneFile = new File(path);
        Scanner phoneScanner = new Scanner(phoneFile);
        setSpecs(phoneScanner);
    }

    // getters
    public String getName() { return name; }
    public String getBrand() { return brand; }
    public LocalDate getReleaseDate() { return releaseDate; }
    public Double getWeight() { return weight; }
    public Integer getResolutionX() { return resolutionX; }
    public Integer getResolutionY() { return resolutionY; }
    public Double getDepth() { return depth; }
    public Double getWidth() { return width; }
    public Double getHeight() { return height; }
    public Integer getStorage() { return storage; }
    public Integer getRAM() { return RAM; }
    public String getChipset() { return chipset; }
    public String getColor() { return color; }
    public Integer getBattery() { return battery; }


    public int getMaxStringLength(){
        int[] lengths = {
                name.length(),
                brand.length(),
                releaseDate.toString().length(),
                weight.toString().length() + 1, //g
                resolutionX.toString().length() + resolutionY.toString().length() + 3, //x, px
                width.toString().length() + height.toString().length() + depth.toString().length() + 4, //x, x, mm
                storage.toString().length() + 2, //GB
                RAM.toString().length() + 2, //GB
                chipset.length(),
                color.length(),
                battery.toString().length() + 3 //mAh
        };

        int maxStringLength = 0;
        for (int length:lengths) {
            maxStringLength = Math.max(maxStringLength, length);
        }
        return maxStringLength + 1;
    }

    private void setSpecs(Scanner phoneScanner)
    {
        while(phoneScanner.hasNextLine())
        {
            String line = phoneScanner.nextLine();
            String header = line.substring(0,line.indexOf(':'));
            String content = line.substring(line.indexOf(':') + 1);
            if(content.equals("")) { continue; }
            switch (header) {
                case "name" -> name = content;
                case "brand" -> brand = content;
                case "releaseDate"-> releaseDate = LocalDate.parse(content);
                case "weight" -> weight = Double.parseDouble(content);
                case "resolution" -> {
                    resolutionX = Integer.parseInt(content.substring(0,content.indexOf('x')));
                    resolutionY = Integer.parseInt(content.substring(content.indexOf('x') + 1));
                }
                case "dimensions" -> {
                    height = Double.parseDouble(content.substring(0,content.indexOf('x')));
                    width = Double.parseDouble(content.substring(content.indexOf('x') + 1, content.lastIndexOf('x')));
                    depth = Double.parseDouble(content.substring(content.lastIndexOf('x') + 1));
                }
                case "chipset" -> chipset = content;
                case "storage" -> storage = Integer.parseInt(content);
                case "RAM" -> RAM = Integer.parseInt(content);
                case "color" -> color = content;
                case "battery" -> battery = Integer.parseInt(content);
            }
        }
    }

    @Override
    public String toString() {
        return  name + "\n" +
                brand + "\n" +
                releaseDate + "\n" +
                weight + "g\n" +
                resolutionX + "px\n" +
                resolutionY + "px\n" +
                height + "mm\n" +
                width + "mm\n" +
                depth + "mm\n" +
                storage + "GB\n" +
                RAM + "GB\n" +
                chipset + "\n" +
                battery + "mAh\n" +
                color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Smartphone that = (Smartphone) o;
        return Objects.equals(name, that.name) && Objects.equals(brand, that.brand) && Objects.equals(releaseDate, that.releaseDate) && Objects.equals(weight, that.weight) && Objects.equals(resolutionX, that.resolutionX) && Objects.equals(resolutionY, that.resolutionY) && Objects.equals(depth, that.depth) && Objects.equals(width, that.width) && Objects.equals(height, that.height) && Objects.equals(storage, that.storage) && Objects.equals(RAM, that.RAM) && Objects.equals(chipset, that.chipset) && Objects.equals(color, that.color) && Objects.equals(battery, that.battery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, brand, releaseDate, weight, resolutionX, resolutionY, depth, width, height, storage, RAM, chipset, color, battery);
    }
}
