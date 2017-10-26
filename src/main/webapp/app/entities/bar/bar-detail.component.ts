import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { Bar } from './bar.model';
import { BarService } from './bar.service';

@Component({
    selector: 'jhi-bar-detail',
    templateUrl: './bar-detail.component.html'
})
export class BarDetailComponent implements OnInit, OnDestroy {
    data: any;
    bar: Bar;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private barService: BarService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInBars();
    }

    load(id) {
        this.barService.find(id).subscribe((bar) => {
            this.bar = bar;
            let tmp = [this.bar.from4To6, this.bar.from6To8, this.bar.from8To10, this.bar.from10ToMid, this.bar.fromMidTo2, this.bar.from2To4];
            this.data = {
                labels: ['16h', '18h', '20h', '22h', '00h', '02h'],
                datasets: [
                    {
                        label: "Heures d'affluence",
                        backgroundColor: '#42A5F5',
                        borderColor: '#1E88E5',
                        data: tmp,
                    }
                ]
            }
        });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInBars() {
        this.eventSubscriber = this.eventManager.subscribe(
            'barListModification',
            (response) => this.load(this.bar.id)
        );
    }
}
