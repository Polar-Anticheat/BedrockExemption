package top.polar.bedrock;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.dependency.Dependency;
import org.bukkit.plugin.java.annotation.dependency.DependsOn;
import org.bukkit.plugin.java.annotation.plugin.Description;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.author.Author;
import org.geysermc.floodgate.api.FloodgateApi;
import top.polar.api.event.DetectionAlertEvent;
import top.polar.api.event.MitigationEvent;
import top.polar.api.event.OffenderBanEvent;
import top.polar.api.event.OffenderKickEvent;
import top.polar.api.loader.LoaderApi;

@Plugin(name = "Polar-BedrockExemption", version = "1.0.0")
@Author("Souvlakis")
@DependsOn({@Dependency("floodgate"), @Dependency("PolarLoader")})
@Description("Exempts Bedrock users from Polar anticheat detections")
public class BedrockExemptionPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        LoaderApi.registerEnableCallback(() -> getServer().getPluginManager().registerEvents(new Listener() {

            @EventHandler
            public void onDetection(DetectionAlertEvent event) {
                // We are forced to skip pre-onJoin detections, as there is no null-safe way to acquire the UUID
                if (event.getPlayer() == null)
                    return;

                if (!FloodgateApi.getInstance().isFloodgatePlayer(event.getPlayer().getUniqueId()))
                    return;

                event.setCancelled(true);
            }

            @EventHandler
            public void onMitigation(MitigationEvent event) {
                if (event.getPlayer() == null)
                    return;

                if (!FloodgateApi.getInstance().isFloodgatePlayer(event.getPlayer().getUniqueId()))
                    return;

                event.setCancelled(true);
            }

            @EventHandler
            public void onBan(OffenderBanEvent event) {
                if (event.getPlayer() == null)
                    return;

                if (!FloodgateApi.getInstance().isFloodgatePlayer(event.getPlayer().getUniqueId()))
                    return;

                event.setCancelled(true);
            }

            @EventHandler
            public void onKick(OffenderKickEvent event) {
                if (event.getPlayer() == null)
                    return;

                if (!FloodgateApi.getInstance().isFloodgatePlayer(event.getPlayer().getUniqueId()))
                    return;

                event.setCancelled(true);
            }

        }, this));
    }
}
