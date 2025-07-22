plugins {
    java
    id("org.springframework.boot") version "3.5.3"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.openapi.generator") version "7.5.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.mybatis.dynamic-sql:mybatis-dynamic-sql:1.5.0")
    implementation("org.mybatis.generator:mybatis-generator-core:1.4.2")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
    implementation("org.openapitools:jackson-databind-nullable:0.2.3")
}

tasks.register<JavaExec>("mybatisGenerate") {
    group = "mybatis"
    description = "Generate MyBatis files"

    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("org.mybatis.generator.api.ShellRunner")

    args = listOf(
        "-configfile", "${projectDir}/src/main/resources/generatorConfig.xml",
        "-overwrite",
        "-verbose"
    )
}

openApiGenerate {
    generatorName.set("spring")
    inputSpec.set("C:/openapi.yaml")
    outputDir.set("${buildDir}/generated/openapi")
    apiPackage.set("com.example.todo_backend.application.controller.api")
    modelPackage.set("com.example.todo_backend.application.dto")
    configOptions.set(mapOf(
        "interfaceOnly" to "true",
        "useJakartaValidation" to "true",
        "useLombok" to "true",
        "skipFormModel" to "true",
        "dateLibrary" to "java8",
        "useBeanValidation" to "true",
        "useJakartaEe" to "true",
        "useSpringBoot3" to "true"
    ))
}

tasks.register<Copy>("copyGeneratedOpenApiToSource") {
    dependsOn(tasks.named("openApiGenerate"))

    from("${buildDir}/generated/openapi/src/main/java") 
    
    into("src/main/java")
}

tasks.named("compileJava") {
    dependsOn(tasks.named("copyGeneratedOpenApiToSource"))
}

tasks.named("bootJar") {
    dependsOn(tasks.named("copyGeneratedOpenApiToSource"))
}