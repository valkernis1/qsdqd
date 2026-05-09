package org.geyser.extension.packorder;

import org.geysermc.geyser.api.event.EventRegistrar;
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineResourcePacksEvent;
import org.geysermc.geyser.api.extension.Extension;
import org.geysermc.geyser.api.pack.ResourcePack;

import java.util.ArrayList;
import java.util.List;

public class PackOrderExtension implements Extension, EventRegistrar {

    private static final String PACK_1 = "CrayonCraft Bedrock.mcpack";
    private static final String PACK_2 = "Actions-and-Stuff-1.9.mcpack";

    @org.geysermc.geyser.api.event.Subscribe
    public void onDefineResourcePacks(GeyserDefineResourcePacksEvent event) {
        List<ResourcePack> packs = new ArrayList<>(event.resourcePacks());
        packs.sort((a, b) -> {
            String nameA = a.path().getFileName().toString();
            String nameB = b.path().getFileName().toString();
            return Integer.compare(priority(nameA), priority(nameB));
        });
        event.resourcePacks().clear();
        event.resourcePacks().addAll(packs);
    }

    private int priority(String name) {
        if (name.equals(PACK_1)) return 0;
        if (name.equals(PACK_2)) return 1;
        return 99;
    }
}

