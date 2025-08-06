package com.linku.backend.global.config;

import com.linku.backend.domain.common.enums.Status;
import com.linku.backend.domain.icon.Icon;
import com.linku.backend.domain.icon.repository.IconRepository;
import com.linku.backend.domain.user.User;
import com.linku.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final IconRepository iconRepository;

    @Value("${data-initialization.user.master.email}")
    private String masterEmail;

    @Value("${data-initialization.user.master.password}")
    private String masterPassword;

    @Value("${cloud.aws.s3.bucket-url}")
    private String bucketUrl;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User masterUser = User.builder()
                    .email(masterEmail)
                    .password(masterPassword)
                    .verified(true)
                    .status(Status.ACTIVE)
                    .build();
            userRepository.save(masterUser);
        }

        if (iconRepository.count() == 0) {
            List<Icon> defaultIcons = List.of(
                    createDefaultIcon("default_client", bucketUrl+"/icon_client_default.png"),
                    createDefaultIcon("default_person", bucketUrl+"/icon_person_default.png"),
                    createDefaultIcon("default_filter", bucketUrl+"/icon_filter_default.png"),
                    createDefaultIcon("default_image", bucketUrl+"/icon_image_default.png"),
                    createDefaultIcon("default_txt", bucketUrl+"/icon_txt_default.png")
            );
            iconRepository.saveAll(defaultIcons);
        }
    }

    private Icon createDefaultIcon(String name, String imageUrl) {
        return Icon.builder()
                .name(name)
                .imageUrl(imageUrl)
                .owner(null)
                .isDefault(true)
                .status(Status.ACTIVE)
                .build();
    }
}
