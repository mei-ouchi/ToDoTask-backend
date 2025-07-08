plugins {
	java
	id("org.springframework.boot") version "3.5.3"
	id("io.spring.dependency-management") version "1.1.7"
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
	implementation("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework.boot:spring-boot-devtools")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
	implementation("org.mybatis.dynamic-sql:mybatis-dynamic-sql:1.5.0")
	implementation("org.mybatis.generator:mybatis-generator-core:1.4.2")
	openApiGen("org.openapitools:openapi-generator-cli:7.5.0")
  openApiGen("io.swagger.parser.v3:swagger-parser:2.1.22")
}

val openApiGen: Configuration = configurations.create("openApiGen") {
    isCanBeConsumed = false
    isCanBeResolved = true
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

tasks.register<JavaExec>("openApiGenerateCustom") {
    group = "generation"
    description = "Generate API interfaces and DTOs using OpenAPI Generator CLI directly"
    classpath = openApiGen

    mainClass.set("org.openapitools.codegen.OpenApiGenerator")

    args = listOf(
        "generate",
        "-g", "spring",
        "-i", "${projectDir}/src/main/resources/openapi.yaml",
        "-o", "${layout.buildDirectory.dir("generated").get().asFile.absolutePath}",
        "--api-package", "com.example.todo_backend.presentation.api",
        "--model-package", "com.example.todo_backend.presentation.dto",
        "--additional-properties", "interfaceOnly=true,useJakartaValidation=true,useLombok=true,skipFormModel=true,dateTimeFormat=java.time.LocalDate"
    )

    sourceSets.main {
        java {
            srcDir(layout.buildDirectory.dir("generated/src/main/java").get().asFile)
        }
    }
}