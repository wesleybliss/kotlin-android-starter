import * as fs from 'fs'
import * as path from 'path'
import * as low from 'lowdb'
import * as FileSync from 'lowdb/adapters/FileSync'
import * as faker from 'faker'
import User from './users/user.entity'

const dir = path.join(__dirname, '../data')
const file = path.join(dir, 'db.json')

if (!fs.existsSync(dir)) {
    console.log('Data directory doesn\'t exist, creating', dir)
    fs.mkdirSync(dir)
}

if (!fs.existsSync(file)) {
    console.log('Database file doesn\'t exist, creating', file)
    fs.writeFileSync(file, '', 'utf8')
}

const adapter = new FileSync(file)
export const db = low(adapter)

export function randomNumber(from: number, to: number): number {
    return Math.floor(Math.random() * (to - from + 1) + from)
}

export function initDatabase() {
    
    if (!fs.existsSync(file) || fs.readFileSync(file, 'utf8').length <= 0)
        // Set some defaults (required if your JSON file is empty)
        db.defaults({ users: [] }).write()
        
}

export function seedUsers() {
    
    let nextId = 0
    const users: User[] = []
    
    while (nextId < 1000) {
        
        nextId++
        
        const dob = faker.date.past(50, new Date('Sat Sep 20 1992 21:35:02 GMT+0200 (CEST)'))
        const gender = randomNumber(0, 1)
        
        let country = faker.locales[faker.locale].address.default_country
        if (country && Array.isArray(country))
            country = country[0]
        else
            country = 'United States of America'
        
        users.push(new User({
            
            id: nextId,
            
            email: faker.internet.email(),
            password: 'password',
            
            firstName: faker.name.firstName(gender),
            lastName: faker.name.lastName(gender),
            dob: new Date(dob.getFullYear(), dob.getMonth(), dob.getDate()),
            gender,
            
            address: faker.address.streetAddress(),
            city: faker.address.city(),
            state: faker.address.stateAbbr(),
            zipCode: faker.address.zipCode(),
            country,
            phone: faker.phone.phoneNumber().replace(/-|\(|\)/ig, '')
            
        }))
        
    }
    
    //console.log('users sample', JSON.stringify(this.users.slice(0, 2), null, 4))
    
    db.set('users', users).write()
    
}
