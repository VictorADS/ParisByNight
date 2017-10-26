import { BaseEntity } from './../../shared';

export class Bar implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public photoContentType?: string,
        public photo?: any,
        public startDate?: string,
        public endDate?: string,
        public coverCharge?: number,
        public adresse?: string,
        public type?: string,
        public specialEvent?: string,
        public from4To6?: number,
        public from6To8?: number,
        public from8To10?: number,
        public from10ToMid?: number,
        public fromMidTo2?: number,
        public from2To4?: number,
    ) {
    }
}
