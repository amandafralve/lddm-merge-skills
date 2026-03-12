package com.example.lddm_merge_skills.db

import com.example.lddm_merge_skills.model.Course
import com.example.lddm_merge_skills.repository.CourseRepository
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SupabaseCourseRepository : CourseRepository {

    private val table = SupabaseFactory.client.postgrest["courses"]
    override suspend fun getAll(): List<Course> = withContext(Dispatchers.IO){
        return@withContext table.select().decodeList<Course>()
    }

    override suspend fun getById(id: Int): Course? = withContext(Dispatchers.IO) {
        return@withContext table.select {filter {eq("id", id)}}.decodeSingleOrNull<Course>()
    }

    override suspend fun create(course:Course): Course = withContext(Dispatchers.IO){
        return@withContext table.insert(course) {select()}.decodeSingle<Course>()
    }

    override suspend fun update(
        id: Int,
        course: Course
    ): Course {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: Int) {
        TODO("Not yet implemented")
    }
}