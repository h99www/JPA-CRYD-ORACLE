package com.greedy.data.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.greedy.data")
/* basePackages 하위의 모든 Repository인터페이스를 상속받은 인터페이스들을 감지해서
  interface의 기능을 수행할 클래스들을 동적으로 생성해준다.  */
@EnableJpaRepositories(basePackages = "com.greedy.data")
public class JpaConfiguration {
}
