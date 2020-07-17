package org.jhipster.project;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("org.jhipster.project");

        noClasses()
            .that()
                .resideInAnyPackage("org.jhipster.project.service..")
            .or()
                .resideInAnyPackage("org.jhipster.project.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..org.jhipster.project.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
