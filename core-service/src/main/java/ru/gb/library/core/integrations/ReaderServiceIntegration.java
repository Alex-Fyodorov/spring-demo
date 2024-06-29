package ru.gb.library.core.integrations;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.gb.library.api.ReaderDto;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class ReaderServiceIntegration {
    private final WebClient readerServiceWebClient;
    private final EurekaClient eurekaClient;

    public ReaderDto getReaderById(Long id, String token) {
        return readerServiceWebClient.get()
                .uri(getReaderServiceIp() + "/lib-reader/readers/" + id)
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(ReaderDto.class)
                .block();
    }

    private String getReaderServiceIp() {
        Application application = eurekaClient.getApplication("reader-service");
        List<InstanceInfo> instances = application.getInstances();
        int randomIndex = ThreadLocalRandom.current().nextInt(instances.size());
        InstanceInfo randomInstance = instances.get(randomIndex);
        return "http://" + randomInstance.getIPAddr() + ":" + randomInstance.getPort();
    }
}
