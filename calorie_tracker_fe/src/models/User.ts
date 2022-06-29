import Principal from "./Principal";

export default interface User extends Principal {
    firstName: string,
    lastName: string,
    heightInCM?: number,
    password?: string,
    bodyMass?: {weightInKg: number}    
} 