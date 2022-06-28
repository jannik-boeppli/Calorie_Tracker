import Principal from "./Principal";

export default interface User extends Principal {
    firstName: string,
    lastName: string,
    heightInCM?: number,
    bodyMass?: {weightInKg: number}    
} 