export class Food {
    fdcId: number
    description: string
    dataType: string
    publicationDate: string
    ingredients: string
    brandOwner: string
    scientificName: string
    brandedFoodCategory: string
    foodNutrients: Array<Nutrients>
}

export class Nutrients {
    id: number
    name: string
    unitName: string
    amount: string
    description: string
}