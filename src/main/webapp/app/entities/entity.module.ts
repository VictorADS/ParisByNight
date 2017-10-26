import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ParisByNightBarModule } from './bar/bar.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        ParisByNightBarModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ParisByNightEntityModule {}
