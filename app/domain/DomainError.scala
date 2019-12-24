package domain

import shared.AppError

class DomainError(val message: String, val cause: Throwable = null)
    extends AppError
