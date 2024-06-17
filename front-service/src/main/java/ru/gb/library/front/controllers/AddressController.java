package ru.gb.library.front.controllers;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.library.front.models.AddressResponse;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final EurekaClient eurekaClient;

    @GetMapping("/{serviceName}")
    public AddressResponse getServiceAddress(@PathVariable String serviceName) {
        Application application = eurekaClient.getApplication(serviceName);
        List<InstanceInfo> instances = application.getInstances();
        int randomIndex = ThreadLocalRandom.current().nextInt(instances.size());
        InstanceInfo randomInstance = instances.get(randomIndex);
        return new AddressResponse("http://" + randomInstance.getIPAddr() + ":" + randomInstance.getPort());
    }
}
