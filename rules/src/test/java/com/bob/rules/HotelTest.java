package com.bob.rules;

import com.bob.rules.reguralrule.model.Fire;
import com.bob.rules.reguralrule.model.Room;
import com.bob.rules.reguralrule.model.Sprinkler;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bob on 16/8/10.
 */
public class HotelTest {

    @Test
    public void testHotel() throws Exception {

        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        String[] names = new String[]{"kitchen", "bedroom", "office", "livingroom"};
        Map<String, Room> name2room = new HashMap<>();
        for (String name : names) {
            Room room = new Room(name);
            name2room.put(name, room);
            kieSession.insert(room);
            Sprinkler sprinkler = new Sprinkler(room, false);
            kieSession.insert(sprinkler);
        }

        kieSession.fireAllRules();

        Fire kitchenFire = new Fire(name2room.get("kitchen"));
        Fire officeFire = new Fire(name2room.get("office"));

        FactHandle kitchenFireHandle = kieSession.insert(kitchenFire);
        FactHandle officeFireHandle = kieSession.insert(officeFire);

        kieSession.fireAllRules();

        kieSession.delete(kitchenFireHandle);
        kieSession.delete(officeFireHandle);

        kieSession.fireAllRules();
    }
}