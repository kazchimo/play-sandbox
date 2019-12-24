package adapter

import shared.AppError

class AdapterError(val message: String, val cause: Throwable) extends AppError
