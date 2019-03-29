export class Event {
    id?: number;
    userId: number;
    title: string;
    address: string;
    eventDate: Date;
    created?: Date;
    modified?: Date;

    constructor(userId: number, title: string, address: string, eventDate: Date, created?: Date, modified?: Date, id?: number) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.address = address;
        this.eventDate = eventDate;
        this.created = created;
        this.modified = modified;
    }
}
