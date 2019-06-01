import { UserType } from "./user-type";

export class User {
    id?: number;
    email?: string;
    password?: string;
    username?: string;
    firstName?: string;
    lastName?: string;
    userType?: string;
    created?: Date;
    modified?: Date;

    constructor(id?: number, email?: string, password?: string, username?: string, firstName?: string, lastName?: string, userType?: string, created?: Date, modified?: Date) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
        this.created = created;
        this.modified = modified;
    }
}
