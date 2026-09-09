package com.angular.be.shop.service;

import com.angular.be.shop.models.CartItem;
import com.angular.be.shop.models.Category;
import com.angular.be.shop.models.Product;
import com.angular.be.shop.models.Tag;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ShopService {
    private final Map<Long, CartItem> cartItems = new LinkedHashMap<>();

    private final List<Product> catalog = List.of(
            new Product(
                    1L,
                    "Mechanical Keyboard",
                    BigDecimal.valueOf(99.99),
                    "https://images.unsplash.com/photo-1541140532154-b024d705b90a?w=500",
                    "Tenkeyless mechanical keyboard featuring tactile Cherry MX Brown switches for responsive, quiet typing. Equipped with per-key RGB backlighting, durable doubleshot PBT keycaps, and a detachable USB-C cable for easy transport and cable management. The compact 87-key layout frees up desk space without sacrificing functionality, making it ideal for both competitive gaming and extended work sessions.",
                    Tag.SALE,
                    Category.GAMING,
                    4.5,
                    Map.of(
                            "Switch", "Cherry MX Brown",
                            "Layout", "TKL 87-key",
                            "Keycaps", "Doubleshot PBT",
                            "Backlight", "Per-key RGB",
                            "Connection", "USB-C detachable",
                            "Weight", "1.1 kg"
                    )
            ),
            new Product(
                    2L,
                    "Wireless Mouse",
                    BigDecimal.valueOf(49.50),
                    "https://images.unsplash.com/photo-1527864550417-7fd91fc51a46?w=500",
                    "Ultra-lightweight ergonomic wireless mouse built for precision and comfort during marathon gaming sessions. Powered by a high-accuracy optical sensor with up to 16,000 DPI, it delivers lag-free tracking on virtually any surface. Dual-mode connectivity lets you switch between 2.4 GHz wireless and Bluetooth 5.0, while six programmable buttons and 70 hours of battery life keep you in control all day.",
                    Tag.POPULAR,
                    Category.GAMING,
                    4.2,
                    Map.of(
                            "Sensor", "Optical 16000 DPI",
                            "Connection", "2.4 GHz wireless / Bluetooth 5.0",
                            "Battery", "70 hours",
                            "Buttons", "6 programmable",
                            "Weight", "85 g",
                            "Polling Rate", "1000 Hz"
                    )
            ),
            new Product(
                    3L,
                    "UltraWide Monitor 34\"",
                    BigDecimal.valueOf(349.99),
                    "https://images.unsplash.com/photo-1527800792452-506aacb2101f?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8dWx0cmElMjB3aWRlJTIwbW9uaXRvcnxlbnwwfHwwfHx8MA%3D%3D",
                    "Immersive 34-inch ultrawide curved monitor with a 21:9 aspect ratio and UWQHD resolution (3440 x 1440) for expansive screen real estate. The 144 Hz refresh rate and 1 ms MPRT response time ensure buttery-smooth visuals in fast-paced games, while the 1500R curvature wraps around your field of view for a cinematic experience. Multiple inputs including HDMI 2.1 and DisplayPort 1.4 provide flexible connectivity for multi-device setups.",
                    null,
                    Category.GAMING,
                    3.0,
                    Map.of(
                            "Panel", "VA",
                            "Resolution", "3440 x 1440 UWQHD",
                            "Refresh Rate", "144 Hz",
                            "Response Time", "1 ms MPRT",
                            "Curvature", "1500R",
                            "Ports", "2x HDMI 2.1, 1x DisplayPort 1.4"
                    )
            ),
            new Product(
                    4L,
                    "Noise-Cancelling Headphones",
                    BigDecimal.valueOf(179.00),
                    "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=500",
                    "Premium over-ear wireless headphones with hybrid active noise cancellation that adapts to your environment, blocking out distractions wherever you are. Large 40 mm dynamic drivers deliver rich, Hi-Res audio with deep bass and crystal-clear highs. Enjoy up to 30 hours of playback with ANC enabled, and seamlessly switch between Bluetooth 5.2 and the included 3.5 mm cable for wired listening. Supports LDAC, AAC, and SBC codecs for uncompromised sound quality across all your devices.",
                    null,
                    Category.SOUND,
                    2.0,
                    Map.of(
                            "Driver", "40 mm dynamic",
                            "ANC", "Hybrid Active",
                            "Battery", "30 hours (ANC on)",
                            "Codec", "LDAC, AAC, SBC",
                            "Connection", "Bluetooth 5.2 / 3.5 mm",
                            "Weight", "254 g"
                    )
            ),
            new Product(
                    5L,
                    "USB-C Dock Station",
                    BigDecimal.valueOf(85.20),
                    "https://plus.unsplash.com/premium_photo-1761043248662-42f371ad31b4?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "Compact USB-C dock station that transforms a single laptop port into a full desktop hub. Delivers 100W power passthrough to keep your laptop charged while simultaneously driving a 4K@60Hz display via HDMI 2.0. Expand your workspace with Gigabit Ethernet, dual USB-A 3.0 ports, and a full-size SD plus microSD card reader. The plug-and-play design requires no drivers, and at just 180 g it slips easily into any bag for on-the-go productivity.",
                    Tag.NEW,
                    Category.UTILITY,
                    4.8,
                    Map.of(
                            "Ports", "2x USB-A 3.0, 1x USB-C PD 100W",
                            "Video", "1x HDMI 2.0 (4K@60Hz)",
                            "Ethernet", "Gigabit RJ45",
                            "SD Card", "SD + microSD",
                            "Power Delivery", "100W passthrough",
                            "Weight", "180 g"
                    )
            ),
            new Product(
                    6L,
                    "Desk Mat XL",
                    BigDecimal.valueOf(19.99),
                    "https://images.unsplash.com/photo-1596347909509-5ea01fb4b278?q=80&w=1074&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "Extra-large desk mat that covers your entire work area, providing a smooth and consistent surface for your keyboard and mouse. The microfiber cloth top offers precise tracking for both optical and laser sensors, while the natural rubber base grips firmly to prevent any slipping. Reinforced stitched edges resist fraying even after months of heavy use, and the 4 mm thickness cushions your wrists during long typing sessions.",
                    null,
                    Category.UTILITY,
                    4.0,
                    Map.of(
                            "Dimensions", "900 x 400 mm",
                            "Thickness", "4 mm",
                            "Material", "Microfiber cloth top / natural rubber base",
                            "Edge", "Stitched anti-fray",
                            "Surface", "Control / Speed",
                            "Weight", "380 g"
                    )
            ),
            new Product(
                    7L,
                    "Webcam 4K",
                    BigDecimal.valueOf(129.00),
                    "https://images.unsplash.com/photo-1636569826709-8e07f6104992?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    "Ultra HD webcam that streams crystal-clear 4K video at 30 fps for professional video calls, live streaming, and content creation. Features a wide 90-degree field of view to capture your entire workspace or group meetings, paired with fast dual phase-detection autofocus that locks onto your face in milliseconds. Dual stereo microphones with built-in noise reduction ensure your voice comes through loud and clear, while the universal clip and tripod thread give you flexible mounting options on any monitor or desk setup.",
                    null,
                    Category.OFFICE,
                    1.5,
                    Map.of(
                            "Resolution", "4K (3840 x 2160) @ 30fps",
                            "Field of View", "90° diagonal",
                            "Autofocus", "Dual PD autofocus",
                            "Microphone", "Dual stereo with noise reduction",
                            "Connection", "USB-C / USB-A (adapter included)",
                            "Mount", "Universal clip + tripod thread"
                    )
            )
    );

    public List<Product> getAllProducts() {
        return catalog;
    }

    public Optional<Product> getProductById(Long productId) {
        return this.catalog.stream()
                .filter(item -> Objects.equals(item.id(), productId))
                .findFirst(); 
    }

    public void addToCart(CartItem cartItem) {
        var productId = cartItem.product().id();
        this.cartItems.merge(productId, cartItem,
                (existing, newItem) -> existing.withAddedQuantity(newItem.quantity()));
    }

    public boolean deleteFromCart(Long productId) {
        return this.cartItems.remove(productId) != null;
    }

    /// This is made to reduce the items from the cart, not fully delete it
    public boolean reduceFromCart(Long productId) {
        boolean success = false;

        boolean isIn = this.cartItems.containsKey(productId);

        if (isIn) {
            CartItem actualItem = this.cartItems.get(productId);
            int newQuantity = actualItem.quantity() - 1;
            if (newQuantity <= 0) {
                deleteFromCart(productId);
            } else {
                this.cartItems.put(productId, new CartItem(actualItem.product(), newQuantity));
            }
            success = true;
        }
        return success;
    }

    public void emptyCart() {
        this.cartItems.clear();
    }

    public List<CartItem> getCartItems() {
        return List.copyOf(cartItems.values());
    }
}
